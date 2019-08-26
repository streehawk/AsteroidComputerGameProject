package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

public class BorderLayoutForm extends Form {
	public BorderLayoutForm() { 
		setLayout(new BorderLayout());
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Button addAsteroid = new Button("Add Asteroid");	
		addAsteroid.getAllStyles().setPadding(Component.TOP, 10);
		addAsteroid.getAllStyles().setPadding(Component.BOTTOM, 10);
		
		leftContainer.add(BorderLayout.WEST, addAsteroid);
	}

}
