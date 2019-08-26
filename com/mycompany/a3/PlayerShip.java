package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class PlayerShip extends Ship implements Isteerable, IDrawable, ICollider{
	private final int MAXMISSILE = 10;
	
	PlayerMissileLauncher playerMissileLauncher;
	
	public PlayerShip() {
		setLocation(740.5, 680.5);
		setColour(255, 0, 0);
		setSpeed(0);
		setDirection(0);
		setMissileCount(MAXMISSILE);
		setSize(30);
		playerMissileLauncher = new PlayerMissileLauncher(getLocation());
		
	}
	
	public void draw(Graphics g, Point p) {
		setPoint(p);
		g.setColor(getColor());
		g.setColor(ColorUtil.BLUE);
		double currentX = this.getLocation().getX();
		double currentY = this.getLocation().getY();
		g.fillTriangle((int)(p.getX() + currentX-(getSize()/2)), (int)(p.getY() + currentY-(getSize()/2)), 
				(int)(p.getX() + currentX+(getSize()/2)), (int)(p.getY() + currentY-(getSize()/2)), 
				(int)(p.getX() + currentX) ,(int)(p.getY() + currentY+(getSize()/2)));
		
		g.setColor(ColorUtil.GREEN);
		g.drawLine(getRight(), getTop(), getLeft(), getTop());
		g.drawLine(getRight(), getBottom(), getLeft() , getBottom());
		g.drawLine(getRight(), getTop(), getRight() , getBottom());
		g.drawLine(getLeft(), getTop(), getLeft() , getBottom());
	}	
	
	public void move(int timeEventRate, Dimension dCmpSize) {
		if (getSpeed() > 0) {
			super.move(timeEventRate, dCmpSize);
			playerMissileLauncher.setLocation(this.getLocation().getX(), this.getLocation().getY());
		}
		
	}
	
	
	public void changeSpeed(int value) {
		if (value == 0) {	
			if (getSpeed() != 0) {					// checking if the speed is 0 then it won't decrease anymore
				setSpeed(getSpeed() - 5);
				playerMissileLauncher.setSpeed(getSpeed() -5);
			}			
		}else {
			setSpeed(getSpeed() + 5);	
			playerMissileLauncher.setSpeed(getSpeed() +5);
		}
	}


	public void turnShip(int value) {
		if (value == 0) {
			steerLeft();		
		}else {
			steerRight();
		}	
	}
	
	public void turnLauncher(int value) {
		if (value == 0) {
			playerMissileLauncher.steerLeft();
		}else {
			playerMissileLauncher.steerRight();
		}	
	}
	
	public void jump() {
		setLocation(740.5, 680.5);	
		playerMissileLauncher.setLocation(740.5, 680.5);
	}

	
	public void steerLeft() {
		setDirection(getDirection() - 5);		
	}

	@Override
	public void steerRight() {
		setDirection(getDirection() + 5);		
	}

	public PSMissile missileFire() {
		if (getMissileCount() <= 0) {
			System.out.println("You have no missile left!! Go to SpaceStation to reload missiles");
			return null;
		} else {
			setMissileCount(getMissileCount() - 1);	
			return new PSMissile(playerMissileLauncher.getLocation(), playerMissileLauncher.getDirection(), this.getSpeed());
		}	
	}
	
	public void loadMissile() {
		setMissileCount(MAXMISSILE);   
	}
	
	
	public int getMaxmissile() {
		return MAXMISSILE;
	}
	
	public String toString() {
		String string = "PlayerShip: ";
		String parentString = super.toString() + " Missile Launcher dir=" + playerMissileLauncher.getDirection(); 
		return string + parentString + "\n";  
	}

	@Override
//	public boolean collidesWith(ICollider otherObject) {
		
//		
//		boolean result = false;
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
//			System.out.println("Playership collides");
//			}
//		   return result ;
//	}

	public void handleCollision(ICollider otherObject) {
		if(otherObject instanceof Asteroid) {
			setCrashed(true);		
		}
		
		if(otherObject instanceof NPSMissile) {
			setExploded(true);		
		}
		
		if(otherObject instanceof NonPlayerShip) {
			setHit(true);		
		}
		
		if(otherObject instanceof SpaceStation) {
			setLoadMissile(true);		
		}
		
		
		
	}

	@Override
	public int getLeft() {
		return getPoint().getX() + (int)getLocation().getX()-(getSize()/2);
	}

	@Override
	public int getRight() {
		return getPoint().getX() + (int)getLocation().getX()+(getSize()/2);
	}

	@Override
	public int getBottom() {
		return getPoint().getY() + (int)getLocation().getY()+(getSize()/2);
	}

	@Override
	public int getTop() {
		return getPoint().getY()+ (int)getLocation().getY()-(getSize()/2);
	}

		
}
