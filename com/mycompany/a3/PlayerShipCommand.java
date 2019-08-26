package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class PlayerShipCommand extends Command implements ActionListener{ 
	
	private GameWorld gw;

	public PlayerShipCommand(GameWorld gw) {
		super("Add PlayerShip");
		this.gw = gw;		
	}
	
	public void actionPerformed(ActionEvent evt) {
//		System.out.println("Pointer Pressed x and y: " + evt.getX() + " " + evt.getY());
		gw.addPlayerShip();		
	}

}

