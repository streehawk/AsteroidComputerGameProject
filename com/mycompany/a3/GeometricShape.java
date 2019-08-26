package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public abstract class GeometricShape implements ISelectable {
	private boolean isSelected;
	public void setSelected(boolean yesNo) { isSelected = yesNo; } public boolean isSelected() { 
		return isSelected; 
		}
	abstract void draw(Graphics g, Point pCmpRelPrnt);
	abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt); 
}
