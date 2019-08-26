package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
//Design Pattern page:67
public class GameWorldProxy extends Observable implements IGameWorld {
	private GameWorld realGameWorld;
	
	public GameWorldProxy (GameWorld gw) {
		realGameWorld = gw;
	}


	public GameObjectCollection objects() {
		return realGameWorld.objects();
	}


	@Override
	public int getScore() {
		return realGameWorld.getScore();
	}


	@Override
	public int getTimeElapsed() {
		return realGameWorld.getTimeElapsed();
	}
	
	public int getLives() {
		return realGameWorld.getLives();
	}
	
	public int getMissileCount() {
		return realGameWorld.getMissileCount();
	}


	@Override
	public boolean getSound() {
		return realGameWorld.getSound();
	}


	@Override
	public boolean getPaused() {
		return realGameWorld.getPaused();
	}
}
