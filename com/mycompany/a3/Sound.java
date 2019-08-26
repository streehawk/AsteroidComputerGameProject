package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
private Media m;
	
	private Sound soundPSFires;
	private Sound psAsteroidCollides;
	private Sound backGroundSound;
	
	 public Sound(String fileName){ 
		 try{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			//attach a runnable to run when media has finished playing //as the last parameter
			m = MediaManager.createMedia(is, "audio/wav");
			}
			 catch(Exception e){
				 e.printStackTrace();
		    }
		}
	 
	public void pause(){ 
		m.setTime(0);
		m.pause();
		} //pause playing the sound
	public void play(){
		m.setTime(0);
		m.play();
		} //continue playing from where we have left off
	
	//entered when media has finished playing
//	  public void run() {
//		//start playing from time zero (beginning of the sound file)
//	    m.setTime(0);
//	    m.play();
//	   }
	  
	 

}
