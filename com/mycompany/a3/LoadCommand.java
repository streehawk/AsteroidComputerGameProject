package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class LoadCommand extends Command implements ActionListener {
	private GameWorld gw;
	public LoadCommand(GameWorld gw) {
		super("Reload");
		this.gw = gw;				
	}
	
	public void actionPerformed(ActionEvent evt) {
		//gw.loadMissile();
		
	} 	

}

