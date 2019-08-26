package com.mycompany.a3;
import java.util.Random;

public abstract class FixGameObject extends GameObject{
	private int id;
	public void setLocation() {
		setLocation(new Random().nextFloat()* this.getMaxX(), new Random().nextFloat()* this.getMaxY());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

