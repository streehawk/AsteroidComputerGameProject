package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class MissileLauncherRightCommand extends Command implements ActionListener {
	private GameWorld gw;
	public MissileLauncherRightCommand(GameWorld gw) {
		super("Missile Launcher turn Right");
		this.gw = gw;				
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.missileLauncherRight();		
	} 	

} 
