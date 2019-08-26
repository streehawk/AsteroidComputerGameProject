package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixGameObject implements IDrawable, ICollider{
	private int blinkRate;
	boolean isBlinking = true;

	
	public SpaceStation() {
		setColour(10,10,50);
		setLocation();
		setBlinkRate(new Random().nextInt(4) + 1);
	}

	public void setBlinkRate(int blinkRate) {
		this.blinkRate = blinkRate;
	}
	
	int getBlinkRate() {
		return blinkRate;
	}	
	
	public void draw(Graphics g, Point p) {
		g.setColor(getColor());
		if(isBlinking) {
			g.fillRect((int)(p.getX() + this.getLocation().getX()), (int)(p.getY() + this.getLocation().getY()), 10, 25);
		}
		
			
	}	
	
	public String toString() {
		return "SpaceStation: " + super.toString() + " Blink Rate=" + blinkRate + "\n";
	}

	public void setBlinking(int i) {
		if (i%blinkRate == 0) {
			isBlinking = !isBlinking;
		}
		
		
			
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		if(otherObject instanceof PlayerShip) {
			setLoadMissile(true);		
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
