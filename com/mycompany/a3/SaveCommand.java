package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class SaveCommand extends Command implements ActionListener {
	private GameWorld gw;		
	public SaveCommand(GameWorld gw) {
		super("Save");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent evt) {
		gw.save();
	}

}
