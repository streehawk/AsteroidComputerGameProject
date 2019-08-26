package com.mycompany.a3;

public abstract class Ship extends MoveableGameObject {
	private int missileCount;
	
	public void setMissileCount(int missileCount) {
		this.missileCount = missileCount;
	}

	public int getMissileCount() {
		return missileCount;
	}
	
public String toString() {
		
		String parentString = super.toString(); 
		String mCount = " Missiles=" + missileCount; 
		return parentString + mCount;  
	}

}
