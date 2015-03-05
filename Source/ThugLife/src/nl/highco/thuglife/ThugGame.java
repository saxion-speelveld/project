package nl.highco.thuglife;

import nl.saxion.act.playground.model.Game;
import nl.saxion.act.playground.model.GameBoard;
import nl.highco.thuglife.objects.*;




public class ThugGame extends Game{
	
public static final String TAG = "thug game";

//private MainActivity activity;

private String[][] map = new String[20][20];

	public ThugGame(MainActivity activity) {
		super(new ThugGameboard());
		
		//this.activity = activity; 
		// moet erbij als er nieuwe componenten aan het main.xml word toe gevoegd
		
		initGame();
		
		ThugGameBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);
		
		gameView.setFixedGridSize(gameBoard.getWidth(),gameBoard.getHeight());
	}
	
	public void initGame(){
		GameBoard board = getGameBoard();
		board.removeAllObjects();
		
		board.addGameObject(new Player(), 0, 8);
		
		board.updateView();
	}

}
