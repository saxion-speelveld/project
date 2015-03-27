package nl.highco.thuglife.objects;

import nl.highco.thuglife.ThugGame;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;



public class Player extends GameObject{
	public static final String PLAYER_IMAGE_R = "player_r";
	public static final String PLAYER_IMAGE_U = "player_u";
	public static final String PLAYER_IMAGE_L = "player_l";
	public static final String PLAYER_IMAGE_D = "player_d";
	
	private boolean alive = true;
	/**
	 * orientation 0 == rechts
	 * 			   1 == omhoog
	 * 			   2 == links
	 * 			   3 == beneden
 	 */
	private int orientation;
	
	public Player(){
		orientation = 0;
	}
	
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
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// de on update methode
	public void onUpdate(GameBoard gameBoard){
		int newPosX = getPositionX();
		int newPosY = getPositionY();
		
		if(orientation == 0){
			newPosX = getPositionX() + 1;
			newPosY = getPositionY();	
		}else if(orientation == 1){
			newPosX = getPositionX();
			newPosY = getPositionY() - 1;
		}else if(orientation == 2){
			newPosX = getPositionX() - 1;
			newPosY = getPositionY();
		}else if(orientation == 3){
			newPosX = getPositionX();
			newPosY = getPositionY() + 1;
		}
	
		
		
		// If new position is over the edge of the board, do nothing
		if (newPosX >= gameBoard.getWidth()) {
			return;
		}
		
		// Check if there is a object on the new position
		GameObject objectAtNewPos = gameBoard.getObject(newPosX, newPosY);
		if (objectAtNewPos != null) {

			
			if (objectAtNewPos instanceof Wall) {
				return;
			}
			if(objectAtNewPos instanceof Player) {
				return;
			}
			if(objectAtNewPos instanceof Police) {
				return;
			}
			if(objectAtNewPos instanceof Shop) {
				ThugGame game = ((ThugGame) gameBoard.getGame());
				game.enterShop();
				game.stopTimers();
				return;
			}
			if(objectAtNewPos instanceof Weed){
				gameBoard.removeObject(objectAtNewPos);
				ThugGame game = ((ThugGame) gameBoard.getGame());
				game.addWiet();
				game.updateScoreWCWW();
			}
		}
		
		// Move wombat to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//richting bepalen
	public void setRichtingOmhoog() {
		orientation = 1;
	}
	
	public void setRichtingRechts() {
		orientation = 0;
	}
	
	public void setRichtingBeneden() {
		orientation = 3;
	}
	
	public void setRichtingLinks() {
		orientation = 2;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// player state
	public void setPlayerToDeadState(){
		alive = false;
	}
	public boolean getAliveState(){
		return alive;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// useles maar moet er in staan
	public void onTouched(GameBoard gameBoard) {
		
	}
}
