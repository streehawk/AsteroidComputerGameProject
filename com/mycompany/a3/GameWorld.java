package com.mycompany.a3;


import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;

public class GameWorld extends Observable implements IObservable, IGameWorld {
	Random random = new Random();
	private Vector<IObserver> myObserverList = new Vector<IObserver>();  
	private GameObjectCollection gObjCollection; 
	private boolean shipExists = false;
	private boolean paused;
	//sound variables 
	private boolean sound;
	private Sound psFiresSound;
	private Sound astPSCollide;
	private Sound astMissExp;
	private Sound gameOver;
	private Sound astNPSCollides;
	private Sound asteroidsCollide;
	private BGSound bgSound;
	private int score = 0;
	private int lives = 3;
	private int timer = 0;
	
	public GameWorld() {
			bgSound = new BGSound("backGround.mp3");
			psFiresSound = new Sound("fire.wav");
			astPSCollide = new Sound("asteroid-ps-collides.wav");
			astMissExp = new Sound("asteroids-missile-explode.wav");
			gameOver = new Sound("game_ends.wav");
			astNPSCollides = new Sound("astNPScollide.wav");
			asteroidsCollide = new Sound("twoAstCollide.wav");
			setSound(true);
	}
	
	//Design Pattern notes page:67
	public void notifyObservers() {
		GameWorldProxy proxy = new GameWorldProxy(this);
		for (IObserver o : myObserverList) {
			o.update(proxy, null);
		}
	}
	
	public void init(MapView mv, PointsView pv) {
		gObjCollection = new GameObjectCollection();
		
	}
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score ;
	}

	

	
	//Add new asteroid
	public void addNewAsteroid() {
		Asteroid asteroid = new Asteroid();
		gObjCollection.add(asteroid);	
		System.out.println("A new Asteroid has been created");
		notifyObservers(); 
	}

	//Add new non-playerShip
	public void addNewNonPlayerShip() {
		NonPlayerShip nonPlayerShip = new NonPlayerShip();
		gObjCollection.add(nonPlayerShip);
		System.out.println("A new Non Player Ship has been created");
		notifyObservers();
		
	}

	//Add SpaceStation
	public void addSpaceStation() {
		SpaceStation SpaceStation = new SpaceStation();
		gObjCollection.add(SpaceStation);
		System.out.println("A new SpaceStation has been created");
		notifyObservers();
	}
	
	//Add PlayerShip
	public void addPlayerShip() {
		if(shipExists) {
			System.out.println("Playership already exists");
				return;    
		} else {
			PlayerShip playerShip = new PlayerShip();
			gObjCollection.add(playerShip);
			shipExists = true;
			System.out.println("A new Player Ship and Missile Launcher has been created");
			notifyObservers();
		}
	}

	//Increase speed of PS
	public void increase() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof PlayerShip) {
				((PlayerShip)o).changeSpeed(1);
				System.out.println("Increased speed by 5");
				notifyObservers();
				return;
			}				
		} System.out.println("Ship does not exist");
		
	}

	//Decrease speed of PS
	public void decrease() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof PlayerShip) {
				((PlayerShip)o).changeSpeed(0);
				System.out.println("Decreased speed by 5");
				notifyObservers();
				return;
			}				
		} System.out.println("Ship does not exist");
		
	}

	//PS turn left direction 
	public void left() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof PlayerShip) {
				((PlayerShip)o).turnShip(0);
				System.out.println("Turn left by 5");
				notifyObservers();
				return;
			}				
		} System.out.println("Ship does not exist");
		
	}

	
//	//PS turn right direction 
	public void right() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof PlayerShip) {
				((PlayerShip)o).turnShip(1);
				System.out.println("Turn right by 5");
				notifyObservers();
				return;
			}				
		} System.out.println("Ship does not exist");
		
	}

	
//	//Missile Launcher of PS turn left
	public void missileLauncherLeft() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof PlayerShip) {
				((PlayerShip)o).turnLauncher(0);
				System.out.println("Missile launch turn clockwise by 5 degree");
				notifyObservers();
				return;
			}				
		} System.out.println("Ship does not exist");
		
	}
	
	//Missile Launcher of PS turn right
	public void missileLauncherRight() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof PlayerShip) {
				((PlayerShip)o).turnLauncher(1);
				System.out.println("Missile launch turn anti-clockwise by 5 degree");
				notifyObservers();
				return;
			}				
		} System.out.println("Ship does not exist");
		
	}


	//Launch missile of PS
	public void fire() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof PlayerShip) {
				PSMissile missile = ((PlayerShip)o).missileFire();
				if(missile != null) {
					gObjCollection.add(missile);
					if(sound)
						psFiresSound.play();
					System.out.println("Launched missile from PlayerShip");
					notifyObservers();
					return;					
				}
				return;
			}				
		} System.out.println("Ship does not exist");
		
	}

	
	//Launch missile of non-PlayerShip
	public void npsFire() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof NonPlayerShip) {
				NPSMissile npsMissile = ((NonPlayerShip)o).launchFire();
				if(!(npsMissile == null)) {
					gObjCollection.add(npsMissile);
					System.out.println("Launched a missile from Non-PlayerShip");
					notifyObservers();
				}
				if (new Random().nextInt(10) < 3) {
					return;
				}
			}				
		} System.out.println("Non PlayerShip does not exist");
		
	}

	
	//PS returns to its default position 
	public void jump() {
		IIterator theElements = gObjCollection.getIterator();
		while(theElements.hasNext()) {
			GameObject o = theElements.getNext();
			if(o instanceof PlayerShip) {
				((PlayerShip)o).jump();
				System.out.println("PlayerShip returned to default position (512,384)");
				notifyObservers();
				return;
			}			
		} System.out.println("Ship does not exist");			
	}
	//Load missiles to the max in PS
		public void loadMissile(GameObject curObj, GameObject otherObject) {
				if(curObj instanceof PlayerShip) {
					((PlayerShip)curObj).loadMissile();
					System.out.println("Reloaded missiles to the max (10).");
				}else {
					((PlayerShip)otherObject).loadMissile();
					System.out.println("Reloaded missiles to the max (10).");
				}		
				notifyObservers();
				return;
			
		}
			
		//PS missile hits Asteroid
		//TODO fix this! 
		public void kill(GameObject curObj, GameObject otherObject) {		
								gObjCollection.remove(curObj);
								gObjCollection.remove(otherObject);
								astMissExp.play();
								setScore(5);
								System.out.println("Missile and Asteroid Struck!");
								this.setChanged();
								notifyObservers();
								return;
		
		}
		

		//PS missile hits NPS
		//TODO fix this
		public void eliminate (GameObject curObj, GameObject otherObject) {
			
								gObjCollection.remove(curObj);
								gObjCollection.remove(otherObject);
								setScore(5);
								System.out.println("Missile and NPS Struck!");
								this.setChanged();
								notifyObservers();
								return;
							
		}

		//NPS missile hits PS
		//TODO fix this
		public void exploded(GameObject curObj, GameObject otherObject) {
			if (curObj instanceof PlayerShip) {
				gObjCollection.remove(otherObject);
				lives--;
				if(lives <= 0) {
					deathTriggered();
				}
				else {
					jump();
				}
			}
			else {
				gObjCollection.remove(curObj);
				lives--;
				if(lives <= 0) {
					deathTriggered();
				}
				else {
					jump();
				}
			}
			System.out.println("NPS missile and PS Struck!");
			notifyObservers();			
	}
		

		//Asteroid hits PS
		public void crashed(GameObject curObj, GameObject otherObject ) {
			if (curObj instanceof PlayerShip) {
				gObjCollection.remove(otherObject);
				if(sound)
					astPSCollide.play();
				lives--;
				if(lives <= 0) {
					deathTriggered();
				}
				else {
					jump();
				}
			}
			else {
				gObjCollection.remove(curObj);
				astPSCollide.play();
				lives--;
				if(lives <= 0) {
					deathTriggered();
				}
				else {
					jump();
				}		
				
			}
			
			
			System.out.println("Asteroid and PS Struck!");
			notifyObservers();				
			
	}
			

		//Asteroids hit each other
		public void exterminated(GameObject curObj, GameObject otherObject) {
			if(sound)
				asteroidsCollide.play();
			gObjCollection.remove(otherObject);
			gObjCollection.remove(curObj);		
			System.out.println("Two Asteroids Struck!");
			notifyObservers();			
	}	

	
	//NPS and PS collide	
		//TODO fix this
	public void hit(GameObject curObj, GameObject otherObject) {
		if (curObj instanceof PlayerShip) {
			gObjCollection.remove(otherObject);
			lives--;
			if(lives <= 0) {
				deathTriggered();
			}
			else {
				jump();
			}
		}
		else {
			gObjCollection.remove(curObj);
			lives--;
			if(lives <= 0) {
				deathTriggered();
			}
			else {
				jump();
			}
		
		}	

		System.out.println("NPS and PS Struck!");
		notifyObservers();
		return;
}
		
		
		

		//NPS and Asteroid collides
		public void impacted(GameObject curObj, GameObject otherObject) {
			
			if(sound) 
				astNPSCollides.play();
			gObjCollection.remove(curObj);
			gObjCollection.remove(otherObject);
			System.out.println("NPS and asteroid struck!");
			this.setChanged();
			notifyObservers();
			return;			
						
				 
				
					//System.out.println("Required GameObjects do not exist!");		
		}
		
		
		
		//Time elapses 
		public void ticked(int timeEventRate, Dimension dCmpSize) {
				IIterator theElements = gObjCollection.getIterator();
				
				while(theElements.hasNext()) {
					GameObject o = theElements.getNext();
					if(o instanceof Imovable) {
						((Imovable)o).move(timeEventRate, dCmpSize);					
						IIterator theElement = gObjCollection.getIterator();
						while(theElement.hasNext()) {
							GameObject obj = theElement.getNext();
							if(obj instanceof PSMissile) {
								if (((PSMissile)obj).getFuel() <= 0 ){
									gObjCollection.remove(obj);
									System.out.println("Player missile removed!");
									return;
								}
							}
							if(obj instanceof NPSMissile) {
								if (((NPSMissile)obj).getFuel() <= 0 ){
									gObjCollection.remove(obj);
									System.out.println("NPSlayer missile removed!");
									return;
					
								}
							}
						}
						
					}
					else {
						((SpaceStation)o).setBlinking(timer+1);
					}
					
				}
				
				
				checkCollusion();	
				timer++;	
				notifyObservers();
				
		}
		public void checkCollusion() {
			IIterator iter = gObjCollection.getIterator();
			// check if moving caused any collisions
			iter = gObjCollection.getIterator();
			while(iter.hasNext()){
				ICollider curObj = (ICollider)iter.getNext();
				// get a collidable object // check if this object collides with any OTHER object
				IIterator iter2 = gObjCollection.getIterator();
				while(iter2.hasNext()){
					ICollider otherObj = (ICollider) iter2.getNext() ; // get a collidable object
					// check for collision
					if(otherObj!=curObj){// make sure it's not the SAME object
						if(curObj.collidesWith(otherObj)){
							curObj.handleCollision(otherObj);
							
							if(((GameObject)curObj).isCrashed()) {
								crashed(((GameObject)curObj),((GameObject)otherObj));	//Asteroid collides PS
							}else if(((GameObject)curObj).isImpacted()) {
								impacted(((GameObject)curObj),((GameObject)otherObj));	//Asteroid collides NPS
							}else if(((GameObject)curObj).isKilled()) {
								kill(((GameObject)curObj),((GameObject)otherObj));
							}else if(((GameObject)curObj).isEliminated()) {
								eliminate(((GameObject)curObj),((GameObject)otherObj));
							}else if(((GameObject)curObj).isExploded()) {
								exploded(((GameObject)curObj),((GameObject)otherObj));	
							}else if(((GameObject)curObj).isHit()) {
								hit(((GameObject)curObj),((GameObject)otherObj));		//NPS collides PS
							}else if(((GameObject)curObj).isExterminated()) {
								exterminated(((GameObject)curObj),((GameObject)otherObj));	
							}else if(((GameObject)curObj).isLoadMissile()) {
								loadMissile(((GameObject)curObj),((GameObject)otherObj));
							}
						}
					} 		
				}
			}
			
		}
		
			
	
		
		public void quit() {
			boolean isQuit = Dialog.show("User wish to quit","Are you sure you want to quit?", "No", "Yes");
			if (!isQuit) {
				System.exit(0);
			}
		}
			
		public int getTimeElapsed() {		
			return timer;
		}
		public int getLives() {
			return lives;
		}

		public void setLives(int lives) {
			this.lives = lives;
		}
					

		
		public GameObjectCollection objects() {
			return gObjCollection;		
		}

		public void addObserver(IObserver observer) {
			myObserverList.add(observer);
		}	

		public int getMissileCount() {
			if (shipExists) {
				IIterator i = gObjCollection.getIterator();
				while(i.hasNext()) {
					GameObject gObj = i.getNext();
					if(gObj instanceof PlayerShip) {
						return ((PlayerShip)gObj).getMissileCount();
					}
				}
			}
			return 0;
		}
		
		//If ship has no live left
		private void deathTriggered() {
			if(sound)
				gameOver.play();
			boolean reset = Dialog.show("Game Over!", "Do you want to reset?", "No", "Yes");
			if (!reset) {
				gObjCollection = new GameObjectCollection();
				shipExists = false;
				lives = 3;
				score = 0;
				timer = 0;
				notifyObservers();
			}
			else {
				System.exit(0);
			}
		}

		public void newCommand() {
			System.out.println("Create new File");
			
		}

		public void save() {
			System.out.println("Saved");			
		}

		public void undo() {
			System.out.println("Undo");
			
		}

		public void about() {
			Dialog.show("Project 2", "Name: Rayyan Saeed\n Course: CSC133\n", "OK", null);		
		}

		
		
		public void setSound(boolean soundStatus) {
			sound = soundStatus;
			if (sound) {
				soundOn();
			}
			else {
				soundOff();
			}
		}

		public void soundOn() {
			sound = true;
			bgSound.play();
			this.setChanged();
			notifyObservers();		
		}

		public void soundOff() {
			sound = false;
			bgSound.pause();
			this.setChanged();
			notifyObservers();	
			
		}
		public boolean getSound() {		
			return sound;
		}

		public void Refuel() {
			
			
		}
		public void setPaused(boolean paused) {
			this.paused = paused; 
		}
		
		public boolean getPaused() {
			return paused;
		}
			

		
}
	

