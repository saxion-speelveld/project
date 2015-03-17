package nl.highco.thuglife;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import nl.saxion.act.playground.model.Game;
import nl.saxion.act.playground.model.GameBoard;
import nl.highco.thuglife.objects.*;




public class ThugGame extends Game implements Observer{
	
public static final String TAG = "thug game";

private MainActivity activity;

//map size
	private final static int MAP_WIDTH = 20;
	private final static int MAP_HIGHT = 20;
	
//map (can be for now only be with a random map)	
	final Handler HANDLER = new Handler();
	private String[][] map;
	
	// player
	private Player player;	
	private ArrayList<Police> politie = new ArrayList<Police>();
	
	// gameboard
	private GameBoard gameBoard;
	
	//timer
	private Timer timer;
	

	public ThugGame(MainActivity activity) {
		super(new ThugGameboard(MAP_WIDTH,MAP_HIGHT));
		
		this.activity = activity; 
		// moet erbij als er nieuwe componenten aan het main.xml word toe gevoegd
		
		
		//starts the game
		initGame();
		
		//sets the game view to the game board
		ThugGameBoardView gameView = activity.getGameBoardView();
		gameBoard = getGameBoard();
		gameBoard.addObserver(this);
		gameView.setGameBoard(gameBoard);
		
		
	// /////////////////////////////////////////////////////////////////////////////////////////////
				// Swipe controls
				gameView.setOnTouchListener(new OnSwipeTouchListener(activity) {
					public void onSwipeTop() {
						Log.i("touchListener", "omhoog");
						player.setRichtingOmhoog();
					}

					public void onSwipeRight() {
						Log.i("touchListener", "rechts");
						player.setRichtingRechts();
					}

					public void onSwipeLeft() {
						Log.i("touchListener", "links");
						player.setRichtingLinks();
					}

					public void onSwipeBottom() {
						Log.i("touchListener", "onder");
						player.setRichtingBeneden();
					}

					public boolean onTouch(View v, MotionEvent event) {
						return gestureDetector.onTouchEvent(event);
					}

				});	
	///////////////////////////////////////////////////////////////////////////////////////////////////	
				
				
		//decides what kind of map format to use
		gameView.setFixedGridSize(gameBoard.getWidth(),gameBoard.getHeight());
		
	}
	
	public void initGame(){
		
		//getting a map
		MapGenerator mapgen = new MapGenerator(MAP_WIDTH,MAP_HIGHT);
		map = mapgen.getStandardMap2();
		
		// setting up the board		
		GameBoard board = getGameBoard();
		board.removeAllObjects();
		
		player = new Player();
		//add player
		board.addGameObject(player, 1, 10);
		
		// add shop
		board.addGameObject(new Shop(), 5, 10);
		
		//hier aanmaken
		
		//Police
		Police p1 = new Police();
		Police p2 = new Police();
		Police p3 = new Police();
		board.addGameObject(p1, 4, 4);
		board.addGameObject(p2, 10, 10);
		board.addGameObject(p3, 16, 16);

		politie.add(p1);
		politie.add(p2);
		politie.add(p3);
		/////
		
		//add load map onto field
		for(int x = 0; x < MAP_WIDTH; x++){
			for(int y = 0; y < MAP_HIGHT; y++){
				if(map[x][y].equals("w")){
					if(board.getObject(x, y) == null){
						board.addGameObject(new Wall(), x, y);
					}
				}
			}
		}
		board.updateView();
	}
	
	
	public void enterShop(){
		activity.gotoShopView();
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
// Timer
	
	
	public void startTimer() {
		// maakt een timer die de handler en de runnable oproept elke halve
		// seconde.
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdateGUI();
			}
		}, 0, 250);
	}
	
	public void stopTimer(){
		timer.cancel();
	}
	// De runnable die je aan de handler meegeeft.
	final Runnable runnable = new Runnable() {
		public void run() {
			player.onUpdate(gameBoard);
			for(int i = 0; i < politie.size(); i++) {
				politie.get(i).onUpdate(gameBoard);
			}
		}
	};

	// Methode waarmee je de runnable aan de handler meegeeft.
	public void UpdateGUI() {
		HANDLER.post(runnable);
	}

	// testen of player alive is
	private boolean isPlayerAlive(){
		for(int x = 0; x < MAP_WIDTH; x++){
			for(int y = 0; y < MAP_HIGHT; y++){
				if(gameBoard.getObject(x, y) instanceof Player){
					Player player = (Player) gameBoard.getObject(x, y);
					return player.getAliveState();
				}
			}
		}
		return false;
	}
	
	
	public void update(Observable observable, Object data) {
		
		if(!isPlayerAlive()){
			stopTimer();
			activity.setText("game Over");
		}
		
	}
	
	
}
