package com.evolve.multimedia.EvoLMPL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This sample demonstrates loading a video via Xuggler and rendering it
 * to a LibGDX texture.
 * 
 * Controls: Touch to play / pause, Enter to stop.
 * 
 * @author ajs
 */
public class VideoScreen implements Screen, InputProcessor {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private VideoPlayer textureProvider;
	public Sprite sprite;
	
	private String video_path = "mov/big_buck_bunny_trailer_400p.ogg";

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
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		textureProvider.update(dt);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
	}

	@Override
	public void show() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		camera.zoom = camera.zoom - 0.5f;
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		
		textureProvider = new VideoPlayer(video_path, this);
		//textureProvider.play();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
