package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class RightSoundCommand extends Command implements ActionListener {
	private GameWorld gw;
	public RightSoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;				
	}
	
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Souncd clicked");
		
	} 	

} 
