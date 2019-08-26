package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class QuitCommand extends Command implements ActionListener{
	private GameWorld gw;

	public QuitCommand(GameWorld gw) {
		super("Quit");
		this.gw = gw;		
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.quit();
	}

}
