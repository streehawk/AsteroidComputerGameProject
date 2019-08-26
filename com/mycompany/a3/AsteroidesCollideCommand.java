package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class AsteroidesCollideCommand extends Command implements ActionListener {
	private GameWorld gw;
	public AsteroidesCollideCommand(GameWorld gw) {
		super("Asteroides' collide");
		this.gw = gw;				
	}
	
	public void actionPerformed(ActionEvent evt) {
//		gw.exterminated();
	}
}


