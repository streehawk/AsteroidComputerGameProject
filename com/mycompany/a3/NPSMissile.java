package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class NPSMissile extends MoveableGameObject implements IDrawable, ICollider{
	private int fuel;
	private int size = 50;
	
	public NPSMissile(Point2D location, int direction, int speed) {
		setLocation(location.getX(),location.getY());
		setColour(0,0,0);
		setSpeed(speed + 10);
		setDirection(direction);
		setFuel(10);
	}
	
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	public int getFuel() {
		return this.fuel;
	}
	
	public void move(int time, Dimension dCmpSize) {
			setFuel(this.fuel - 1);
			super.move(time, dCmpSize);
	}
	
	public void draw(Graphics g, Point p) {
		setPoint(p);
		g.setColor(getColor());
		g.drawLine((int)(p.getX() + this.getLocation().getX()), (int)(p.getY() + this.getLocation().getY()),
				(int)(p.getX() + this.getLocation().getX()), (int)(p.getY() + this.getLocation().getY()+size));
	}

	public String toString() {
		return "NPSMissile: " + super.toString() + " fuel= " + fuel + "\n";
	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		int thisCenterX = (int) (getLocation().getX() + (size/2)); // find centers 
		int thisCenterY = (int) (getLocation().getY() + (size/2));
		int otherCenterX = (int)(((GameObject)otherObject).getLocation().getX());
		int otherCenterY = (int)(((GameObject)otherObject).getLocation().getY());
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		     // find square of sum of radii
		int thisRadius = size/2;
		int otherRadius = ((GameObject)otherObject).getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
		+ otherRadius*otherRadius); if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
		   return result ;
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		if(otherObject instanceof PlayerShip) {
			setExploded(true);		
		}	
		
	}

	@Override
	public int getLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBottom() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTop() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
}
