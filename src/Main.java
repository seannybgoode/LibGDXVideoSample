
 

import java.io.IOException;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.evolve.tests.VideoTest;


/** 
 * Evolve Lite Media Library testrunner. 
 * By Sean Brophy 2015
 * */

public class Main 
{
	
	public static void main(String[] args) throws IOException 
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.useGL30 = false;

		cfg.vSyncEnabled = false; 
		cfg.foregroundFPS = 60;
		cfg.backgroundFPS = 60;
		cfg.width = 1280;
		cfg.height = 720;

		cfg.stencil = 16;
		
		
		//locks window size
		cfg.resizable = false;
		
		VideoTest game = new VideoTest();
		Application app = new LwjglApplication(game, cfg);

	}

}
 