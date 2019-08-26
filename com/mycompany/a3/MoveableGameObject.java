package com.mycompany.a3;

import com.codename1.ui.geom.Dimension;

public abstract class MoveableGameObject extends GameObject implements Imovable  {
	
	public MoveableGameObject() {
	}
	private int speed;
	private int direction;
		
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		if (direction >= 360)
		{
			direction -= 360;
		}
		if (direction < 0) {
			direction += 360;
		}
		
		this.direction = direction; 
	}
	
	public void move(int time, Dimension dimension) {
		int angle = 90 - direction;
		int distance = speed * (time/10);
		double deltaX = Math.cos(Math.toRadians(angle)) * distance;
		double deltaY = Math.sin(Math.toRadians(angle)) * distance;
		setLocation(this.getLocation().getX() + deltaX, this.getLocation().getY() + deltaY);
		
		if (this.getLocation().getX() >= dimension.getWidth()) {
			setLocation(dimension.getWidth()-5, this.getLocation().getY());
			setDirection(-direction);
		}
		
		if (this.getLocation().getX() <= 0) {
			setLocation(5, this.getLocation().getY());
			setDirection(-direction);
		}
		
		System.out.println(dimension.getHeight());
		
		if (this.getLocation().getY() >= dimension.getHeight()) {
			setLocation(this.getLocation().getX(), dimension.getHeight()-5);
			setDirection(180 - direction);
		}
		
		if (this.getLocation().getY() <= 0) {
			setLocation(this.getLocation().getX(), 5);
			setDirection(180 - direction);
		}
	}
	
public String toString() {
		
		String parentString = super.toString(); 
		String string = " speed= " + speed + " dir= " + direction; 
		return parentString + string;  
	}
			

}
