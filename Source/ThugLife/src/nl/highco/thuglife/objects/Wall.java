package nl.highco.thuglife.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Wall extends GameObject{
	public static final String WALL_IMAGE = "wall";
	@Override
	public String getImageId() {
		
		return WALL_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		
		
	}
	
}
