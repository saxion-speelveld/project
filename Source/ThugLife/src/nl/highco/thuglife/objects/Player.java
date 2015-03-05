package nl.highco.thuglife.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Player extends GameObject{
	public static final String PLAYER_IMAGE = "player";
	
	
	public String getImageId() {
		return PLAYER_IMAGE;
	}

	
	public void onTouched(GameBoard gameBoard) {
		
		
	}

}
