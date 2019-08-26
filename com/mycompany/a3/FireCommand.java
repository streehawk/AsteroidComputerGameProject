package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class FireCommand extends Command implements ActionListener {
	private GameWorld gw;
	public FireCommand(GameWorld gw) {
		super("Missile fire");
		this.gw = gw;				
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.fire();
		
	} 	

}  

