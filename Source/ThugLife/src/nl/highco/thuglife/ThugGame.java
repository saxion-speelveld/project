package nl.highco.thuglife;

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
	private String[][] map;

	public ThugGame(MainActivity activity) {
		super(new ThugGameboard(MAP_WIDTH,MAP_HIGHT));
		
		//this.activity = activity; 
		// moet erbij als er nieuwe componenten aan het main.xml word toe gevoegd
		
		
		//starts the game
		initGame();
		
		//???????
		ThugGameBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);
		
		//decides what kind of map format to use
		gameView.setFixedGridSize(gameBoard.getWidth(),gameBoard.getHeight());
	}
	
	public void initGame(){
		
		//getting a map
		MapGenerator mapgen = new MapGenerator(MAP_WIDTH,MAP_HIGHT);
		map = mapgen.getRandomMap();
		
		// setting up the board		
		GameBoard board = getGameBoard();
		board.removeAllObjects();
		
		//add player
		board.addGameObject(new Player(), 0, 8);
		
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

}
