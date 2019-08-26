package com.mycompany.a3;

import com.codename1.ui.geom.Point2D;

public class NPSMissileLauncher extends MoveableGameObject {
	
	public NPSMissileLauncher (Point2D location, int color, int speed, int direction){
		setSpeed(speed);
		setDirection(direction);
		setLocation(location.getX(), location.getY());
	}


	

	
}
