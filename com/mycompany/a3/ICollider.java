package com.mycompany.a3;

public interface ICollider {
	public boolean collidesWith(ICollider otherObject);
	public void handleCollision(ICollider otherObject);
	
	public int getLeft();
	public int getRight();
	public int getBottom();
	public int getTop();
	
}
