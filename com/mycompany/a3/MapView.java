package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.FlowLayout;

public class MapView extends Container implements IObserver {
	IGameWorld gw;
	
	public MapView(GameWorld gw) {
		setLayout(new FlowLayout());
		this.getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(ColorUtil.WHITE);
		this.gw = gw;
	}
	
	public void update (Observable o, Object arg) {
	//	System.out.println();
		GameObjectCollection gObj = ((IGameWorld)o).objects();
		IIterator iterator = gObj.getIterator(); 
		while(iterator.hasNext()) {
			GameObject obj = iterator.getNext();
			System.out.print(obj.toString());
		}
		repaint();
//		System.out.println();
	}

	
	public void pointerPressed(int x, int y) {
		if(gw.getPaused()) {
			//make pointer location relative to parent’s origin
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(x, y);
			Point pCmpRelPrnt = new Point(getX(), getY());
			IIterator iterator = gw.objects().getIterator();
			while (iterator.hasNext()) {
				GameObject gObj = iterator.getNext();
				if(gObj instanceof ISelectable) {
					if (((ISelectable)gObj).contains(pPtrRelPrnt, pCmpRelPrnt)) {
						((ISelectable)gObj).setSelected(true);
					} else {
						((ISelectable)gObj).setSelected(false);
						}
				}
				
			}
			repaint(); 
		}
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		IIterator iterator = gw.objects().getIterator();
		while (iterator.hasNext()) {
			Point p = new Point(this.getAbsoluteX(), this.getY());
			IDrawable drawObject = (IDrawable)iterator.getNext();
			drawObject.draw(g, p);
		}
	}		
}
		
	
