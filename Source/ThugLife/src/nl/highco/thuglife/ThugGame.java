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
private int money, score, wiet;

//map size
	private final static int MAP_WIDTH = 20;
	private final static int MAP_HIGHT = 20;
	
//map (can be for now only be with a random map)	
	final Handler POLICEHANDLER = new Handler();
	final Handler PLAYERHANDLER = new Handler();
	private String[][] map;
	
	// player
	private Player player;	
	private ArrayList<Police> politie = new ArrayList<Police>();
	
	// gameboard
	private GameBoard gameBoard;
	
	//timer
	private Timer policeTimer;
	private Timer playerTimer;

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
		
		//score en money 0
		score = 0;
		money = 0;
		wiet = 10;
		
		//add player
		player = new Player();
		board.addGameObject(player, 1, 10);
		
		// add shop
		board.addGameObject(new Shop(), 5, 10);
		
		//wiet
		board.addGameObject(new Weed(), 3, 15);
		
		//Police
		politie.clear();
		
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
	
	public void reset(){
		initGame();
	}
	

	/**
	 * adds money to total of players' money
	 * @param aantal amount of money to be added
	 */
	public void addMoney(int aantal){
		money += aantal;
		gameBoard.updateView();
	}
	
	/**
	 * deducts money of total of players' money
	 * @param aantal amount of money to be deducted
	 */
	public void deductMoney(int aantal){
		money -= aantal;
		gameBoard.updateView();
	}
	
	/**
	 * adds 50 points to score when player collides with weed object
	 */
	public void updateScoreWCWW(){
		score += 50;
		gameBoard.updateView();
	}
	
	/**
	 * adds one weed
	 */
	public void addWiet(){
		wiet ++;
		gameBoard.updateView();
	}
	/**
	 * deducts the weed according to the given value
	 * @param aantal
	 */
	public void deductWiet(int aantal){
		wiet -= aantal;
		gameBoard.updateView();
	}
	
	/**
	 * returns the total amount of money the player has
	 * @return total amount of money player has
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * returns the total score the player has
	 * @return total score the player has
	 */
	public int getScore(){
		return score;
	}
	/**
	 * returns the total amount of wiet the player has
	 * @return wiet
	 */
	public int getWiet(){
		return wiet;
	}
	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
// Timer
	
	// police timer
	public void startPoliceTimer() {
	
		// maakt een timer die de handler en de runnable oproept elke halve
		// seconde.
		policeTimer = new Timer();
		policeTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdateGUIPolice();
			}
		}, 0, 250);
	}
	
	private void stopPoliceTimer(){
		policeTimer.cancel();
	}
	
	//player timer
	public void startPlayerTimer(int speed) {
		
		// maakt een timer die de handler en de runnable oproept elke halve
		// seconde.
		playerTimer = new Timer();
		playerTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				UpdateGUIPlayer();
			}
		}, 0, speed);
	}
	
	private void stopPlayerTimer(){
		playerTimer.cancel();
	}
	
	// De runnable voor de police die je aan de handler meegeeft.
	final Runnable policeRunnable = new Runnable() {
		public void run() {
			for(int i = 0; i < politie.size(); i++) {
				politie.get(i).onUpdate(gameBoard);
			}
		}
	};
	
	// De runnable voor de player die je aan de handler meegeeft.
		final Runnable playerRunnable = new Runnable() {
			public void run() {
				player.onUpdate(gameBoard);
			}
		};

	// Methode waarmee je de runnable aan de handler meegeeft.
	public void UpdateGUIPolice() {
		POLICEHANDLER.post(policeRunnable);
	}
	
	public void UpdateGUIPlayer() {
		PLAYERHANDLER.post(playerRunnable);
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
	
	public void stopTimers(){
		stopPlayerTimer();
		stopPoliceTimer();
	}
	
	public void update(Observable observable, Object data) {
		
		if(!isPlayerAlive()){
			stopTimers();
			activity.setGameState("game Over");
		}
		
		activity.updateMoneyLabels();
		activity.updateScoreLabel();
		activity.updateWietLabels();
		
	}
	
	
}
