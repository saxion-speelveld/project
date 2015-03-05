package nl.highco.thuglife.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Player extends GameObject{
	public static final String PLAYER_IMAGE_R = "player right";
	public static final String PLAYER_IMAGE_U = "player up";
	public static final String PLAYER_IMAGE_L = "player left";
	public static final String PLAYER_IMAGE_D = "player down";
	
	private int orientation;
	
	
	public String getImageId() {
		if(orientation == 0){
			return PLAYER_IMAGE_R;
		}else if(orientation == 1){
			return PLAYER_IMAGE_U;
		}else if(orientation == 2){
			return PLAYER_IMAGE_L;
		}else{
			return PLAYER_IMAGE_D;
		}
		
	
	}

	
	public void onTouched(GameBoard gameBoard) {
		orientation ++;
		if(orientation == 4){
			orientation = 0;
		}
		gameBoard.updateView();
	}

}
