package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class SoundCommand extends Command implements ActionListener{
	private GameWorld gw;

	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;		
	}
	
	public void actionPerformed(ActionEvent evt) {
			gw.setSound(!gw.getSound());
	}
} 
