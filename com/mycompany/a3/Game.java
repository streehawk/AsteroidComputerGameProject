package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
	Random random = new Random();
	private GameWorld gw;
	private MapView mv;
	private PointsView pv; 
	private BGSound bgs;
	UITimer timer;
	int luck = new Random().nextInt(10);
	
	
	private Button left;
	private LeftTurnCommand leftTurnCommand;
	private Button right;
	private LeftTurnCommand rightTurnCommand;
	private myButtonStyle addAsteroid;
	private myButtonStyle pause;
	private PauseCommand pauseCommand;
	private myButtonStyle refuel;
	private CheckBox checkSound;
	private SoundCommand soundCommand;
	

	
	private boolean isPaused = false;
	
	private class myButtonStyle extends Button{
		private myButtonStyle(String name) {
			super(name);
			getAllStyles().setBgTransparency(225);
			getAllStyles().setBgColor(ColorUtil.rgb(169,169,169));	
			getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 0));
			getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		}
	}
	
	public void PauseStatus(int i) {
		if (i == 0) {
			Pause(timer);		
		}else if(i==1) {
			Resume(timer);
		}
	}
	
	
	public Game() {
		gw = new GameWorld();
		pv = new PointsView(gw);
		mv = new MapView(gw);
		gw.addObserver(mv);
		gw.addObserver(pv);
	 	gw.init(mv, pv);
	 	
	 	//Create timer and provide a runnable
	 	timer = new UITimer(this);
	 	//make the timer tick every second and bind it to this form
	 	timer.schedule(100,true,this); 	
	 	
	 	 	
	 	
	 	//Creating layout-------------------------------------------------------------------------
	 	setLayout(new BorderLayout());
		Container leftContainer = new Container(new GridLayout(7,1));
		//Container centerContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container topContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		topContainer.add(new Label("Asteroid"));
				
		
		leftContainer.getAllStyles().setPadding(Component.TOP, 10);
		//centerContainer.getAllStyles().setPadding(Component.TOP, 10);
		
		//Adding PointView and MapView to the form
		add(BorderLayout.NORTH, pv);
		add(BorderLayout.CENTER, mv);
		
		//Creating Hamburger menu
		Toolbar hamBurgerMenu = new Toolbar();
		setToolbar(hamBurgerMenu);
		hamBurgerMenu.getAllStyles().setPadding(1, 0, 0, 0);
		Label label = new Label("Asteroid Game");
		hamBurgerMenu.setTitleComponent(label);
		
		
					 	
	 	// Creating buttons and hamburgerMenu---------------------------------- 
		addAsteroid = new myButtonStyle("Add Asteroid");	
		AsteroidCommand addAsteroidCommand = new AsteroidCommand(gw);
		addAsteroid.setCommand(addAsteroidCommand);
		addKeyListener(97, addAsteroidCommand);
		hamBurgerMenu.addCommandToSideMenu(addAsteroidCommand);
			
//		myButtonStyle addNewNP = new myButtonStyle("Add Non-Playership");
//		NonPlayerShipCommand addNPCommand = new NonPlayerShipCommand(gw);
//		addNewNP.setCommand(addNPCommand);
//		addKeyListener('y', addNPCommand);
//		hamBurgerMenu.addCommandToSideMenu(addNPCommand);
		
		myButtonStyle addNewSpaceStation = new myButtonStyle("Add SpaceStation");	
		SpaceStationCommand addSpaceStationCommand = new SpaceStationCommand(gw);
		addNewSpaceStation.setCommand(addSpaceStationCommand);
		addKeyListener('b', addSpaceStationCommand);
		hamBurgerMenu.addCommandToSideMenu(addSpaceStationCommand);
		
		
		myButtonStyle addNewPlayerShip = new myButtonStyle("Add Non-Playership");	
		PlayerShipCommand addPlayerShipCommand = new PlayerShipCommand(gw);
		addNewPlayerShip.setCommand(addPlayerShipCommand);
		addKeyListener('s', addPlayerShipCommand);
		hamBurgerMenu.addCommandToSideMenu(addPlayerShipCommand);
		
		refuel = new myButtonStyle("Refuel");	
		RefuelCommand refuelCommand = new RefuelCommand(gw);
		refuel.setCommand(refuelCommand);
		
		myButtonStyle incSpeed = new myButtonStyle("Increase Speed");	
		IncSpeedCommand incSpeedCommand = new IncSpeedCommand(gw);
		incSpeed.setCommand(incSpeedCommand);
		addKeyListener(-91, incSpeedCommand);
		
		myButtonStyle decSpeed = new myButtonStyle("Decrease Speed");	
		DecSpeedCommand decSpeedCommand = new DecSpeedCommand(gw);
		decSpeed.setCommand(decSpeedCommand);
		addKeyListener(-92, decSpeedCommand);
		
		left = new Button("Turn left");	
		leftTurnCommand = new LeftTurnCommand(gw);
		left.setCommand(leftTurnCommand);
		addKeyListener(-93, leftTurnCommand);
		
		right = new Button("Turn right");	
		RightTurnCommand rightTurnCommand = new RightTurnCommand(gw);
		right.setCommand(rightTurnCommand);
		addKeyListener(-94, rightTurnCommand);
		
		myButtonStyle missileLauncherLeft = new myButtonStyle("Missile Launcher Left");	
		MissileLauncherLeftCommand missileLauncherDirCommand = new MissileLauncherLeftCommand(gw);
		missileLauncherLeft.setCommand(missileLauncherDirCommand);
		addKeyListener(44, missileLauncherDirCommand);
		hamBurgerMenu.addCommandToSideMenu(missileLauncherDirCommand);
		
		myButtonStyle missileLauncherRight = new myButtonStyle("Missile Launcher Right");	
		MissileLauncherRightCommand missileLauncherRightCommand = new MissileLauncherRightCommand(gw);
		missileLauncherRight.setCommand(missileLauncherRightCommand);
		addKeyListener(46, missileLauncherRightCommand);
		hamBurgerMenu.addCommandToSideMenu(missileLauncherRightCommand);
		
		Button fire = new Button("Missile fire");	
		FireCommand fireCommand = new FireCommand(gw);
		fire.setCommand(fireCommand);
		addKeyListener(-90, fireCommand);
		
		myButtonStyle nonPlayerShipFire = new myButtonStyle("Non-playership missile fire");	
		NPSFireCommand npsFireCommand = new NPSFireCommand(gw);
		nonPlayerShipFire.setCommand(npsFireCommand);
		addKeyListener('L', npsFireCommand);
		hamBurgerMenu.addCommandToSideMenu(npsFireCommand);
		
		Button jump = new Button("Jump");	
		jump.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		JumpCommand jumpCommand = new JumpCommand(gw);
		jump.setCommand(jumpCommand);
		addKeyListener('j', jumpCommand);
		
		myButtonStyle loadMissile = new myButtonStyle("Load Missile");	
		LoadCommand loadCommand = new LoadCommand(gw);
		loadMissile.setCommand(loadCommand);
		addKeyListener('l', loadCommand);
		hamBurgerMenu.addCommandToSideMenu(loadCommand);
	
		
		myButtonStyle asteroidesnPSCollide = new myButtonStyle("Asteroid and PS collide");	
		AsteroidesnPSCollideCommand asteroidesnPSCollideCommand = new  AsteroidesnPSCollideCommand(gw);
		asteroidesnPSCollide.setCommand(asteroidesnPSCollideCommand);
		addKeyListener('c', asteroidesnPSCollideCommand);
		hamBurgerMenu.addCommandToSideMenu(asteroidesnPSCollideCommand);
		
		
		myButtonStyle asteroidesCollide = new myButtonStyle("Two Asteroids collide");	
		AsteroidesCollideCommand asteroidesCollideCommand = new  AsteroidesCollideCommand(gw);
		asteroidesCollide.setCommand(asteroidesCollideCommand);
		addKeyListener('x', asteroidesCollideCommand);
		hamBurgerMenu.addCommandToSideMenu(asteroidesCollideCommand);
		
//		myButtonStyle asteroideNPSCollide = new myButtonStyle("NPS and Asteroid collide");	
//		AsteroideNPSCollideCommand asteroideNPSCollideCommand = new  AsteroideNPSCollideCommand(gw);
//		asteroideNPSCollide.setCommand(asteroideNPSCollideCommand);
//		addKeyListener('i', asteroideNPSCollideCommand);
//		hamBurgerMenu.addCommandToSideMenu(asteroideNPSCollideCommand);
		
		pause = new myButtonStyle("Pause");	
		pauseCommand = new PauseCommand(this);
		pause.setCommand(pauseCommand);
		addKeyListener('t', pauseCommand);
		hamBurgerMenu.addCommandToSideMenu(pauseCommand);
		
		//Sound checkBox command
		checkSound = new CheckBox("Sound");
		soundCommand = new SoundCommand(gw);
		checkSound.setCommand(soundCommand);
		hamBurgerMenu.addComponentToSideMenu(checkSound);
		
		myButtonStyle quit = new myButtonStyle("Quit");	
		quit.getAllStyles().setBgColor(ColorUtil.rgb(200, 200, 200));
		QuitCommand quitCommand = new QuitCommand(gw);
		quit.setCommand(quitCommand);
		addKeyListener('q', quitCommand);
		hamBurgerMenu.addCommandToSideMenu(quitCommand);
		
		//-------------------------------------------------------------------------------------------------
		
		
		
		//Adding keys to West side---------------------------------------------------------------------------
		leftContainer.add(addNewPlayerShip);
		leftContainer.add(addAsteroid);
		//leftContainer.add(addNewNP);
		leftContainer.add(addNewSpaceStation);
		leftContainer.add(refuel);
		//leftContainer.add(incSpeed);
		//leftContainer.add(decSpeed);
		//leftContainer.add(left); 
		//leftContainer.add(right);
		leftContainer.add(missileLauncherLeft);
		leftContainer.add(missileLauncherRight);
		//leftContainer.add(fire);
		//leftContainer.add(nonPlayerShipFire);
		//leftContainer.add(jump);
		//leftContainer.add(loadMissile);
		//leftContainer.add(killMissAst);
		//leftContainer.add(eliminateMissNPS);
		//leftContainer.add(explodePSbyNPSmiss);
		//leftContainer.add(asteroidesnPSCollide);
		//leftContainer.add(asteroidesCollide);
		//leftContainer.add(collideNPSnPS);
		//leftContainer.add(asteroideNPSCollide);
		leftContainer.add(pause);			
		leftContainer.add(quit);
		//---------------------------------------------------------------------------
		
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		add(BorderLayout.WEST, leftContainer);
		
		//making centerview 
		//centerContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		//add(BorderLayout.CENTER, centerContainer);
		
		
		
		
		//Add 'New' command to right side menu
		NewCommand newCommand = new NewCommand(gw);	
		hamBurgerMenu.addCommandToOverflowMenu(newCommand);
		
		//Add 'Save' command to right side menu
		SaveCommand saveCommand = new SaveCommand(gw);
		hamBurgerMenu.addCommandToOverflowMenu(saveCommand);
		
		//Add 'Undo' command to right side menu
		UndoCommand undoCommand = new UndoCommand(gw);
		hamBurgerMenu.addCommandToOverflowMenu(undoCommand);
		
		//Add 'About' command to side menu
		AboutCommand aboutCommand = new AboutCommand(gw);
		hamBurgerMenu.addCommandToOverflowMenu(aboutCommand);
		
		RightSoundCommand rightSoundCommand = new RightSoundCommand(gw);
		hamBurgerMenu.addCommandToOverflowMenu(rightSoundCommand);
		
		//Add 'Quit' command to side menu		
		hamBurgerMenu.addCommandToOverflowMenu(quitCommand);	
		this.show();
	}


	@Override
	public void run() {
		Dimension dCmpSize = new Dimension(mv.getWidth(), mv.getHeight());
		int roll = Game.genRandInt(1, 500);
		if (roll > 250 && roll < 255) {
			gw.addNewNonPlayerShip();
			if(roll >=1 && roll <=250)
				gw.npsFire();
		}		
		gw.ticked(20, dCmpSize);	
	}


	public void Resume(UITimer timer) {
		gw.setPaused(false);
		pause.setText("Pause");
		pauseCommand.setCommandName("Pause");
		timer.schedule(100, true, this);
		isPaused = false;
		left.getCommand().setEnabled(true);
		left.setEnabled(true);
		addKeyListener(-93, leftTurnCommand);
		right.getCommand().setEnabled(true);
		right.setEnabled(true);
		addKeyListener(-94, rightTurnCommand);
		addAsteroid.getCommand().setEnabled(true);
		addAsteroid.setEnabled(true);
		refuel.getCommand().setEnabled(false);
		refuel.setEnabled(true);
		gw.setSound(true);
		
	}


	public void Pause(UITimer timer) {
		gw.setPaused(true);
		pause.setText("Play");
		pauseCommand.setCommandName("Play");
		timer.cancel();
		isPaused = true;
		left.getCommand().setEnabled(false);
		left.setEnabled(false);
		removeKeyListener(-93, leftTurnCommand);
		right.getCommand().setEnabled(false);
		right.setEnabled(false);
		removeKeyListener(-94, rightTurnCommand);
		addAsteroid.getCommand().setEnabled(false);
		addAsteroid.setEnabled(false);
		refuel.getCommand().setEnabled(false);
		refuel.setEnabled(false);
		gw.setSound(false);
		checkSound.getCommand().setEnabled(false);
		soundCommand.setEnabled(false);
		
	}


	public boolean isPaused() {
		return isPaused;
	}
	public static int genRandInt(int min, int max) {
		Random r = new Random();
		int x = r.nextInt((max - min) + 1) + min;
		return x;
		}
}


