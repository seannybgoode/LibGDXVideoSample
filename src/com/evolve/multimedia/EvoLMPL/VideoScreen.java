package com.evolve.multimedia.EvoLMPL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evolve.pixeldefender.GameManager;

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
	private VideoPlayer textureProvider;
	public Sprite sprite;
	
	private String video_path = "mov/big_buck_bunny_trailer_400p.ogg";
	private SpriteBatch fontBatch;
	private BitmapFont font;

	
	public VideoScreen(String video_path)
	{
		
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		textureProvider.dispose();
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
			textureProvider.stop();
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
		if(textureProvider.getState() != VideoPlayer.PlayState.PLAYING) {
			textureProvider.play();
		} else {
			textureProvider.pause();
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
		
		textureProvider.update(dt);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		fontBatch.begin();
		this.printDebugOutput(fontBatch);
		fontBatch.end();
		
	}

	@Override
	public void show() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		this.font  = new BitmapFont(new FileHandle("fnt/Arial.fnt"), 
				new FileHandle("fnt/Arial_0.tga"), false);
		font.setScale(0.5f, 0.5f);
		font.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));

		camera = new OrthographicCamera(1, h/w);
		camera.zoom = camera.zoom - 0.5f;
		batch = new SpriteBatch();
		fontBatch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		
		textureProvider = new VideoPlayer(video_path, this);
		//textureProvider.play();
	}
	
	private void printDebugOutput(SpriteBatch batch)
	{
		font.setColor(Color.WHITE);
		font.draw(batch, "FPS: " + String.format("%d", Gdx.graphics.getFramesPerSecond()), 20, GameManager.VIRTUAL_VP_HEIGHT - 5);
		font.draw(batch, "Audio Timestamp: " + String.format("%d", textureProvider.getAudioTimeStamp()), 100, GameManager.VIRTUAL_VP_HEIGHT - 5);
		font.draw(batch, "Video Timestamp: " + String.format("%d", textureProvider.getVideoTimeStamp()), 300, GameManager.VIRTUAL_VP_HEIGHT - 5);
		font.draw(batch, "AudioPacketsQueued: " + String.format("%d", textureProvider.getNumAudioPackets()), 500, GameManager.VIRTUAL_VP_HEIGHT - 5);
		font.draw(batch, "VideoPacketsQueued: " + String.format("%d", textureProvider.getNumVideoPackets()), 700, GameManager.VIRTUAL_VP_HEIGHT - 5);
		font.draw(batch, "PlayTime(ms): " + String.format("%d", textureProvider.getPlayTimeMilliseconds()), 20, GameManager.VIRTUAL_VP_HEIGHT - 20);
		
	}

	@Override
	public void hide() {
		
	}
}
