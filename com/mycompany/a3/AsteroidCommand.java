package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class AsteroidCommand extends Command implements ActionListener{
	private GameWorld gw;

	public AsteroidCommand(GameWorld gw) {
		super("Add Asteroid");
		this.gw = gw;		
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.addNewAsteroid();		
	}

}
