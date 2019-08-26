package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class JumpCommand extends Command implements ActionListener {
	private GameWorld gw;
	public JumpCommand(GameWorld gw) {
		super("Jump");
		this.gw = gw;				
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.jump();
		
	} 	

}  