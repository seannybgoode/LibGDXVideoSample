package com.evolve.multimedia.evolmpl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * libGDX Screen extension for playing videos. Specify the video file path in the 
 * constructor. Videos should be .ogg format.
 * Controls: Touch to play / pause, Enter to stop.
 * 
 * @author ajs, Sean Brophy
 */
public class VideoScreen implements Screen, InputProcessor {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private VideoPlayer videoPlayer;
	public Sprite sprite;
	
	private String video_path;
	private SpriteBatch fontBatch;
	private BitmapFont font;
	
	private Runnable onComplete;
	private boolean debugOn;
	private Texture tex;
	private float w;
	private float h;

	/** Creates a new video screen that will play the video from the specified path
	 * @param video_path the relative path of the video*/
	public VideoScreen(String video_path)
	{
		this.video_path = video_path;
		onComplete = null;
	}
	
	/** Creates a new video screen that will play the video from the specified path
	 * and will run the specified runnable when the video is finished
	 * @param video_path the video path
	 * @param onComplete the runnable to run on completion*/
	public VideoScreen(String video_path, Runnable onComplete)
	{
		this.onComplete = onComplete;
		this.video_path = video_path;
	}
	
	public void setPixmap(Pixmap pix)
	{
		if(tex != null)
		{
			tex.draw(pix, 0, 0);
		}
		else
		{
			tex = new Texture(pix);
			sprite.setTexture(tex);
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		videoPlayer.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		// Stop on enter
		if(keycode == Keys.ENTER) {
			videoPlayer.stop();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		// Toggle play/pause on touch
		if(videoPlayer.getState() != VideoPlayer.PlayState.PLAYING) {
			videoPlayer.play();
		} else {
			videoPlayer.pause();
		}
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void render(float delta) {
		float dt = Gdx.graphics.getDeltaTime();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		videoPlayer.update(dt);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		fontBatch.begin();
		this.printDebugOutput(fontBatch);
		fontBatch.end();
		if(videoPlayer.videoComplete && onComplete != null)
			onComplete.run();
		
	}

	@Override
	public void show() {
		this.w = Gdx.graphics.getWidth();
		this.h = Gdx.graphics.getHeight();
		this.font  = new BitmapFont(new FileHandle("fnt/Arial.fnt"), 
				new FileHandle("fnt/Arial_0.tga"), false);
		font.getData().setScale(0.5f, 0.5f);
		font.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));

		camera = new OrthographicCamera(1, h/w);
		//camera.zoom = camera.zoom - 1.5f;
		batch = new SpriteBatch();
		fontBatch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		
		videoPlayer = new VideoPlayer(video_path, this);
		//textureProvider.play();
	}
	
	//draws relevant debug info to the screen
	private void printDebugOutput(SpriteBatch batch)
	{
		float h = Gdx.graphics.getHeight();
		if(this.debugOn)
		{
			font.setColor(Color.WHITE);
			font.draw(batch, "FPS: " + String.format("%d", Gdx.graphics.getFramesPerSecond()), 20, h - 5);
			font.draw(batch, "Audio Timestamp: " + String.format("%d", videoPlayer.getAudioTimeStamp()), 100, h - 5);
			font.draw(batch, "Video Timestamp: " + String.format("%d", videoPlayer.getVideoTimeStamp()), 300, h - 5);
			font.draw(batch, "AudioPacketsQueued: " + String.format("%d", videoPlayer.getNumAudioPackets()), 500, h - 5);
			font.draw(batch, "VideoPacketsQueued: " + String.format("%d", videoPlayer.getNumVideoPackets()), 700, h - 5);
			font.draw(batch, "PlayTime(ms): " + String.format("%d", videoPlayer.getPlayTimeMilliseconds()), 20, h - 20);
		}
	}

	@Override
	public void hide() {
		
	}

	public boolean isDebugOn() {
		return debugOn;
	}

	public void setDebugOn(boolean debugOn) {
		this.debugOn = debugOn;
	}

}
