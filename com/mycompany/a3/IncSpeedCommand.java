package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class IncSpeedCommand extends Command implements ActionListener {
	private GameWorld gw;
	public IncSpeedCommand(GameWorld gw) {
		super("Increase Speed");
		this.gw = gw;
				
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.increase();
		
	}
	

}
