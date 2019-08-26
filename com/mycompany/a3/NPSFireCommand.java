package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class NPSFireCommand extends Command implements ActionListener {
	private GameWorld gw;
	public NPSFireCommand(GameWorld gw) {
		super("Non-playership missile fire");
		this.gw = gw;				
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.npsFire();
		
	} 	

} 
