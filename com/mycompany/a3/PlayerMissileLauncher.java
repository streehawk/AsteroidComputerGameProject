package com.mycompany.a3;

import com.codename1.ui.geom.Point2D;

public class PlayerMissileLauncher extends Ship implements Isteerable {
	
	public PlayerMissileLauncher(Point2D location) {
		setLocation(location.getX(),location.getY());
		setSpeed(0);
		setDirection(0);
	}
	
	
	public void steerLeft() {
		setDirection((getDirection() - 5) + 360);
		while (getDirection() > 360) {
			setDirection(getDirection() - 360);
		}
	}
	

	public void steerRight() {
		setDirection((getDirection() + 5) + 360);
		while (getDirection() > 360) {
			setDirection(getDirection() - 360);
		}
	}
}
	
	


