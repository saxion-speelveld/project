package nl.highco.thuglife.objects;

import nl.highco.thuglife.Score;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Weed extends GameObject {
	
	public static final String WEED_IMAGE = "weed";
	public static final int SCORE = 50;
	


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




}
	