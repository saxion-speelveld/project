package nl.highco.thuglife.objects;


import java.util.Random;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Weed extends GameObject {
	
	public static final String WEED_IMAGE = "weed";
	public static final int SCORE = 50;
	private int[] position = new int[2];


	@Override
	public String getImageId() {
		return WEED_IMAGE;
	}
	
	public int getScore(){
		return SCORE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		
	
	}
	
	public void updatePos(GameBoard gameBoard){
        // Instantiate random object
		Random r = new Random();
		
        // Set standard position to 0, 0
		position['x'] = 0;
		position['y'] = 0;
		
		position['x'] = r.nextInt(gameBoard.getWidth());
		position['y'] = r.nextInt(gameBoard.getHeight());
		gameBoard.addGameObject(this, this.position['x'], this.position['y']);
	}

}
	