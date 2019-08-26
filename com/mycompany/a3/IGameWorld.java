package com.mycompany.a3;

public interface IGameWorld {
	public GameObjectCollection objects();
	public int getScore();
	public int getTimeElapsed();
	public int getLives();
	public int getMissileCount();
	public boolean getSound();
	public boolean getPaused();
	
}
