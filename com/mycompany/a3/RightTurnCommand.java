package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class RightTurnCommand extends Command implements ActionListener {
	private GameWorld gw;
	public RightTurnCommand(GameWorld gw) {
		super("Turn Right");
		this.gw = gw;				
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.right();
		
	} 	

} 
	

