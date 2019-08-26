package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

public class PointsView extends Container implements IObserver{
	Label pointsTextLabel = new Label("Points: ");
	Label missCountTextLabel = new Label("Missile Count:");
	Label timeTextLabel = new Label("Elapsed time:");
	Label livesTextLabel = new Label("Lives: ");
	Label soundTextLabel = new Label("Sound: ");
	
	
	
	private Label pointsValueLabel;
	private Label missValueLabel;
	private Label timeValueLabel;
	private Label livesValueLabel;
	private Label soundValueLabel;
	
	
	public PointsView(GameWorld gw) {
		
		pointsValueLabel = new Label("0");		
		pointsTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		missValueLabel = new Label("0");
		pointsTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		timeValueLabel = new Label("0");		
		timeTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		livesValueLabel = new Label("0");		
		livesTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		soundValueLabel = new Label("OFF");		
		soundTextLabel.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		
		
		setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		add(pointsTextLabel);
		add(pointsValueLabel);
		add(timeTextLabel);
		add(timeValueLabel);
		add(missCountTextLabel);
		add(missValueLabel);
		add(livesTextLabel);
		add(livesValueLabel);
		add(soundTextLabel);
		add(soundValueLabel);
		
	}
		
	
	public void update (Observable o, Object arg){
		IGameWorld gw = (IGameWorld) o;
		//Getting player score
		
	//	pointsValueLabel.setText("" + (score > 99 ? "" : "0") + (score > 9 ? "" : "0")+ score);
		pointsValueLabel.setText("" + gw.getScore());
	
		
		//Getting time elapsed
		timeValueLabel.setText("" + gw.getTimeElapsed());
		
		//Getting lives
		livesValueLabel.setText("" + gw.getLives());
		
		//Getting missCount
		missValueLabel.setText("" + gw.getMissileCount());
		
		//Getting Sound
		
		if (gw.getSound())
			soundValueLabel.setText("ON ");
		else
			soundValueLabel.setText("OFF");
		
		
		this.repaint();
		
	}
	
}

