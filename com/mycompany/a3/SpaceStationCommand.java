package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class SpaceStationCommand extends Command implements ActionListener {
	
	private GameWorld gw;
	
	public SpaceStationCommand(GameWorld gw) {
		super("Add SpaceStation");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.addSpaceStation();
	}

}
