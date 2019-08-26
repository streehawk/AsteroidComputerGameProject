package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public abstract class GameObject {
	private String name;
	private int size;
	private Point2D location = new Point2D(0,0);
	private int colour;
	private final double maxX = 1481.0;
	private final double maxY = 1361.0;
	private Point p = new Point(0,0);
	//boolean for crashes
	private boolean crashed = false;
	private boolean impacted = false;
	private boolean killed = false;
	private boolean eliminated = false;
	private boolean exploded = false;
	private boolean hit = false;
	private boolean exterminated = false;
	private boolean loadMissile = false;
	
	
	
	
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	
	public String getName() {
		return name;
	}

	public double getMaxX() {
		return maxX;
	}
	public double getMaxY() {
		return maxY;
	}
	
	public Point2D getLocation() {
		return location;
	}
	
	public void setLocation(double d,double e) {
		location.setX(d);
		location.setY(e);
	}
	
	public int getColor() {
		return this.colour;
	}
	
	public void setColour(int r, int g, int b) {
		this.colour = ColorUtil.rgb(r, g, b);
	}
	
	
	
	public String toString(){
			 
		String string = "loc= " + (Math.round(location.getX()*10.0)/10.0) + ", " + (Math.round(location.getY()*10.0)/10.0) + " color=" + "[" + ColorUtil.red(colour) + "," + ColorUtil.green(colour) + "," + ColorUtil.blue(colour) + "]"; 
		return string;
		}
	
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		/*if(this instanceof NonPlayerShip && otherObject instanceof NonPlayerShip) 
			return false;
		
		if((((ICollider)this).getRight() < otherObject.getLeft()) || (((ICollider)this).getLeft() > otherObject.getRight())) {
			result = false;
		}
			
		if((otherObject.getTop() < ((ICollider)this).getBottom()) || (((ICollider)this).getTop() < otherObject.getBottom())) {
			result = false;
		}
		*/
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
		+ otherRadius*otherRadius); 
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true ;
			//System.out.println("Asteroid collides");
		}
		return result;
	}

	public Point getPoint() {
		return p;
	}

	public void setPoint(Point p) {
		this.p = p;
	}
	
	
	 public void setCrashed(boolean crashed) {
		 this.crashed = crashed; 	 
	 }
	 public void setImpacted(boolean impacted) {
		 this.impacted = impacted; 	 
	 }
	 public void setKilled(boolean killed) {
			this.killed = killed;
	}
	 public void setEliminated(boolean eliminated) {
			this.eliminated = eliminated;
	} 
	 public void setExploded(boolean exploded) {
			this.exploded = exploded;
	}
	 public void setHit(boolean hit) {
			this.hit = hit;
	}
	 public void setExterminated(boolean exterminated) {
			this.exterminated = exterminated;
	}
	 public void setLoadMissile(boolean loadMissile) {
			this.loadMissile = loadMissile;
	}
	 
	 public boolean isCrashed() {
			return crashed;
	}
	 public boolean isImpacted() {
			return impacted;
	}
	public boolean isKilled() {
		return killed;
	}
	public boolean isEliminated() {
		return eliminated;
	}

	public boolean isExploded() {
		return exploded;
	}
	
	public boolean isHit() {
		return hit;
	}

	public boolean isExterminated() {
		return exterminated;
	}

	public boolean isLoadMissile() {
		return loadMissile;
	}

	

	

	

	
	
}
	

//		int thisCenterX = (int) (getLocation().getX() + (size/2)); // find centers 
//		int thisCenterY = (int) (getLocation().getY() + (size/2));
//		int otherCenterX = (int)(((GameObject)otherObject).getLocation().getX());
//		int otherCenterY = (int)(((GameObject)otherObject).getLocation().getY());
//		// find dist between centers (use square, to avoid taking roots)
//		int dx = thisCenterX - otherCenterX;
//		int dy = thisCenterY - otherCenterY;
//		int distBetweenCentersSqr = (dx*dx + dy*dy);
//		     // find square of sum of radii
//		int thisRadius = size/2;
//		int otherRadius = ((GameObject)otherObject).getSize()/2;
//		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
//		+ otherRadius*otherRadius); 
//		if (distBetweenCentersSqr <= radiiSqr) { 
//			result = true ;
//			System.out.println("Asteroid collides");
//		}
			
