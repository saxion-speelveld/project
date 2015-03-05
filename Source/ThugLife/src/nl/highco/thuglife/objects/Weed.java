package nl.highco.thuglife.objects;


import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Weed extends GameObject {
	
	public static final String WEED_IMAGE = "weed";
	


	@Override
	public String getImageId() {
		return WEED_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		/**
		Score score = new Score(); 
		
		int newPosX = (int) (Math.random() * gameBoard.getWidth());
		int newPosY = (int) (Math.random() * gameBoard.getHeight());
		
		gameBoard.addGameObject(this, newPosX, newPosY);
		int oudeScore = score.getScore();
		int newScore = oudeScore + 50;
		score.updateScore(newScore);
		gameBoard.updateView();
		
		**/
	}




}
	
	

	

