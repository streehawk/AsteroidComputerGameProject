package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class NewCommand extends Command implements ActionListener {
	private GameWorld gw;
	public NewCommand(GameWorld gw) {	
		super("New");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent ev) {
		gw.newCommand();
	}
	

}
