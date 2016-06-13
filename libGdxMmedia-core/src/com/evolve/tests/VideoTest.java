package com.evolve.tests;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.evolve.multimedia.evolmpl.VideoScreen;

public class VideoTest extends Game implements ApplicationListener
{

	VideoScreen vs;
	
	public VideoTest()
	{
		this.vs = new VideoScreen("mov/big_buck_bunny_trailer_400p.ogg");
		this.vs.setDebugOn(true);
	}

	@Override
	public void create()
	{
		setScreen(vs);

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}
}
