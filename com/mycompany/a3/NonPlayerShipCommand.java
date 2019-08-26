package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class NonPlayerShipCommand extends Command implements ActionListener{ 
	private GameWorld gw;

	public NonPlayerShipCommand(GameWorld gw) {
		super("Add Non PlayerShip");
		this.gw = gw;		
	}
	
	public void actionPerformed(ActionEvent evt) {
//		System.out.println("Pointer Pressed x and y: " + evt.getX() + " " + evt.getY());
		gw.addNewNonPlayerShip();		
	}

}


