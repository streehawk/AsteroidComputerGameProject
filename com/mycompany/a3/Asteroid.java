package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;



public class Asteroid extends MoveableGameObject implements IDrawable, ICollider, ISelectable {
	
	private boolean isSelected;
	
	public Asteroid() {
		setLocation(new Random().nextFloat()* this.getMaxX(), new Random().nextFloat()* this.getMaxY());
		setColour(123,255,50);
		setSpeed(new Random().nextInt(10));
		setDirection(new Random().nextInt(360));
		setSize(new Random().nextInt((100 - 20)+ 1)+ 20);
		
	}
	
	public String toString() {
		return "Asteroid: " + super.toString() + "\n";
	}

	@Override
	public void draw(Graphics g, Point p) {	
		//TODO Draw all objects
		setPoint(p);
		g.setColor(getColor());
		
		if(isSelected)
			g.setColor(ColorUtil.BLACK);
		
		g.fillArc((int)(p.getX() + this.getLocation().getX()), (int)(p.getY() + this.getLocation().getY()),
			getSize(), getSize(), 0, 360);	
		g.setColor(ColorUtil.MAGENTA);
		//Demo for rectangle collision boundary 
		g.drawLine(getRight(), getTop(), getLeft(), getTop());
		g.drawLine(getRight(), getBottom(), getLeft() , getBottom());
		g.drawLine(getRight(), getTop(), getRight() , getBottom());
		g.drawLine(getLeft(), getTop(), getLeft() , getBottom());
	}

	public void handleCollision(ICollider otherObject)  {
		if(otherObject instanceof Asteroid) {
			setExterminated(true);		
		}
		if(otherObject instanceof PlayerShip) {
			setCrashed(true);		
		}
		if(otherObject instanceof NonPlayerShip) {
			setImpacted(true);		
		}
		if(otherObject instanceof PSMissile) {
			setKilled(true);		
		}
		
	}

	@Override
	public int getLeft() {
		return getPoint().getX()+(int)getLocation().getX();
	}

	@Override
	public int getRight() {
		return getPoint().getX()+(int)getLocation().getX()+getSize();
	}

	@Override
	public int getBottom() {
		return getPoint().getY()+(int)getLocation().getY();
	}

	@Override
	public int getTop() {
		return getPoint().getY()+(int)getLocation().getY()+getSize();
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

	
	


