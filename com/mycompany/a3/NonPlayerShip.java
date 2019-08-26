package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class NonPlayerShip extends Ship implements IDrawable, ISelectable, ICollider {
	private int size;
	char character = 'V';
	int randomSize = new Random().nextInt(10);		//generate random values to determine the size of the ship
	private NPSMissileLauncher npsMissileLauncher;	
	boolean select;

	
	public NonPlayerShip() {
		setLocation(new Random().nextFloat() * getMaxX(), new Random().nextFloat() * getMaxY());
		setColour(35,76,33);

		//Determining randomly the size of a ship
		if (randomSize >= 5) {
			setSize(45);
		}else {
			setSize(30);
		}
		
		setSpeed(new Random().nextInt(10));
		setDirection(new Random().nextInt(360));
		
		npsMissileLauncher = new NPSMissileLauncher(this.getLocation(),this.getColor(), this.getSpeed(), this.getDirection());
		setMissileCount(2);
	}
	
	public NPSMissile launchFire() {
		if (getMissileCount() <= 0) {
			System.out.println("NonPlayerShip has no missile left");
			return null;
		} else {
			setMissileCount(getMissileCount() - 1);	
			return new NPSMissile(npsMissileLauncher.getLocation(), npsMissileLauncher.getDirection(), this.getSpeed());
		}	
	}
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		
		this.size = size;
	}
	
	public String toString() {
			
		String string = "NonPlayerShip: ";
		String parentString = super.toString() + " Missile Launcher dir=" + npsMissileLauncher.getDirection(); 
		return string + parentString + "\n";  
		}
	
	public void move(int timeEventRate, Dimension dCmpSize) {
		super.move(timeEventRate, dCmpSize);
		npsMissileLauncher.setLocation(this.getLocation().getX(), this.getLocation().getY());
	}
	
	public void draw(Graphics g, Point p) {
		setPoint(p);
		g.setColor(getColor());
		int map_X = (int)p.getX();
		int map_Y = (int)p.getY();
		int axis_X = ((int)this.getLocation().getX()) + map_X;
		int axis_Y = ((int)this.getLocation().getY()) + map_Y;
		
		//g.fillRect(axis_X, axis_Y, size, size + 2);
		g.fillArc((int)(p.getX() + this.getLocation().getX()), (int)(p.getY() + this.getLocation().getY()),
				getSize(), getSize(), 0, 180);
		
//		g.drawRect(axis_X - (size/2), axis_Y - (size/2), size/2, size);
//		if(!select) {
//			g.drawChar(character,(int)(p.getX() + this.getLocation().getX()), (int)(p.getY() + this.getLocation().getY()));
//		}else if(select){
//			g.drawChar('Y',(int)(p.getX() + this.getLocation().getX()), (int)(p.getY() + this.getLocation().getY()));
//		}
		
		//Demo for rectangle collision boundary 
		g.setColor(ColorUtil.MAGENTA);
		g.drawLine(getRight(), getTop(), getLeft(), getTop());
		g.drawLine(getRight(), getBottom(), getLeft() , getBottom());
		g.drawLine(getRight(), getTop(), getRight() , getBottom());
		g.drawLine(getLeft(), getTop(), getLeft() , getBottom());
		
//		g.drawLine(p.getX()+(int)getLocation().getX(),p.getY()+(int)getLocation().getY(),
//				p.getX()+(int)getLocation().getX(),p.getY()+(int)getLocation().getY()+(size));
//		
//		
//		
//		g.drawLine(p.getX()+(int)getLocation().getX()+size,p.getY()+(int)getLocation().getY(),
//				p.getX()+(int)getLocation().getX()+size,p.getY()+(int)getLocation().getY()+(size));
//		
//		
//		
//		g.drawLine(p.getX()+(int)getLocation().getX(),p.getY()+(int)getLocation().getY(),
//				p.getX()+(int)getLocation().getX()+size,p.getY()+(int)getLocation().getY());
//		
//		
//		
//		g.drawLine(p.getX()+(int)getLocation().getX(),p.getY()+(int)getLocation().getY()+size,
//				p.getX()+(int)getLocation().getX()+size,p.getY()+(int)getLocation().getY()+size);
			
			
	}

	@Override
	public void setSelected(boolean yesNo) {
		select = yesNo;
		
	}

	@Override
	public boolean isSelected() {
		return select;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean collidesWith(ICollider otherObject) {
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
//		+ otherRadius*otherRadius); if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
//		   return result ;


	@Override
	public void handleCollision(ICollider otherObject) {
		
		if(otherObject instanceof PlayerShip) {
			setHit(true);		
		}
		if(otherObject instanceof Asteroid) {
			setImpacted(true);		
		}
		
		
	}

	public int getLeft() {
		return getPoint().getX() + (int)getLocation().getX();
	}

	@Override
	public int getRight() {
		return getPoint().getX() + (int)getLocation().getX() + getSize();
	}

	@Override
	public int getBottom() {
		return getPoint().getY() + (int)getLocation().getY();
	}

	@Override
	public int getTop() {
		return getPoint().getY()+ (int)getLocation().getY()+(getSize()/2);
	}

}
