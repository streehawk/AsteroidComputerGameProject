package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class PSMissile extends MoveableGameObject implements ICollider, IDrawable, ISelectable {
	private int fuel, size = 30;
	private boolean isSelected;
	
	public PSMissile(Point2D location, int direction, int speed) {
		setLocation(location.getX(),location.getY());
		setColour(100,100,50);
		setSpeed(speed + 10);
		setDirection(direction);
		setFuel(50);
	}
	
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	public int getFuel() {
		return this.fuel;
	}
	
	public void move(int timeEventRate, Dimension dCmpSize) {
		if (getSpeed() > 0) {
			super.move(timeEventRate, dCmpSize);
//			playerMissileLauncher.setLocation(this.getLocation().getX(), this.getLocation().getY());
		}
		setFuel(this.fuel - 1);
	}
	
	public String toString() {
		return "PSMissile: " + super.toString() + " Fuel= " + fuel + "\n";
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		if(otherObject instanceof Asteroid) {
			setKilled(true);		
		}
		
		if(otherObject instanceof NonPlayerShip) {
			setEliminated(true);		
		}
		
		
		
	}


	@Override
	public void draw(Graphics g, Point p) {
		setPoint(p);
		int map_X = (int)p.getX();
		int map_Y = (int)p.getY();
		int axis_X = ((int)this.getLocation().getX()) + map_X;
		int axis_Y = ((int)this.getLocation().getY()) + map_Y;
		if(isSelected)
			g.fillRect(axis_X, axis_Y, size/2, size);
		else
			g.drawRect(axis_X, axis_Y, size/2, size);
	}
	
	
	@Override
	public int getLeft() {
		return getPoint().getX() + (int)getLocation().getX();
	}

	@Override
	public int getRight() {
		return getPoint().getX() + (int)getLocation().getX()+(size/2);
	}

	@Override
	public int getBottom() {
		return getPoint().getY() + (int)getLocation().getY();
	}

	@Override
	public int getTop() {
		return getPoint().getY()+ (int)getLocation().getY()+(size);
	}

	@Override
	public void setSelected(boolean yesNo) {
		isSelected = yesNo;
	}

	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin
		int xLoc = (int) (this.getLocation().getX());// shape location relative
		int yLoc = (int)(this.getLocation().getY());// to parent’s origin
		if ( (px >= xLoc) && (px <= xLoc+ getSize())
		&& (py >= yLoc) && (py <= yLoc+ getSize()) )
		return true; else return false;
	}

	
}
