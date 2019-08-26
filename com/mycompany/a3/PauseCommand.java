package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class PauseCommand extends Command implements ActionListener {
	private Game g;
	//private myButtonStyle button;
	//private boolean isPaused = false;
	
	public PauseCommand(Game g) {
		super("Pause");
		this.g = g;				
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(g.isPaused()) {
			g.PauseStatus(1);
		}else if(!g.isPaused()) {
			g.PauseStatus(0);
		}	
	} 	
} 
