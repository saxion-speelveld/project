package nl.highco.thuglife;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import nl.saxion.act.playground.model.Game;
import nl.saxion.act.playground.model.GameBoard;
import nl.highco.thuglife.objects.*;




public class ThugGame extends Game{
	
public static final String TAG = "thug game";

//private MainActivity activity;

//map size
	private final static int MAP_WIDTH = 20;
	private final static int MAP_HIGHT = 20;
	
//map (can be for now only be with a random map)	
	final Handler HANDLER = new Handler();
	private String[][] map;
	
	// player
	private Player player;	
	
	// gameboard
	private GameBoard gameBoard;
	
	//timer
	private Timer timer;
	

	public ThugGame(MainActivity activity) {
		super(new ThugGameboard(MAP_WIDTH,MAP_HIGHT));
		
		//this.activity = activity; 
		// moet erbij als er nieuwe componenten aan het main.xml word toe gevoegd
		
		
		//starts the game
		initGame();
		
		//???????
		ThugGameBoardView gameView = activity.getGameBoardView();
		gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);
		
		//decides what kind of map format to use
		gameView.setFixedGridSize(gameBoard.getWidth(),gameBoard.getHeight());
		
	}
	
	public void initGame(){
		
		//getting a map
		MapGenerator mapgen = new MapGenerator(MAP_WIDTH,MAP_HIGHT);
		map = mapgen.getStandardMap();
		
		// setting up the board		
		GameBoard board = getGameBoard();
		board.removeAllObjects();
		
		player = new Player();
		//add player
		board.addGameObject(player, 1, 10);
		
		// add shop
		board.addGameObject(new Shop(), 10, 0);
		
		
		
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
		}, 0, 500);
	}
	
	public void stopTimer(){
		timer.cancel();
	}
	// De runnable die je aan de handler meegeeft.
	final Runnable runnable = new Runnable() {
		public void run() {
			player.onUpdate(gameBoard);
		}
	};

	// Methode waarmee je de runnable aan de handler meegeeft.
	public void UpdateGUI() {
		HANDLER.post(runnable);
	}
	
	
}
