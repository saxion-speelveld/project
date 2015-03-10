package nl.highco.thuglife.objects;

import java.util.Random;

import android.util.Log;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Police extends GameObject{
	public static final String POLICE_IMAGE_R = "player right";
	public static final String POLICE_IMAGE_U = "player up";
	public static final String POLICE_IMAGE_L = "player left";
	public static final String POLICE_IMAGE_D = "player down";
	/**
	 * orientation 0 == rechts
	 * 			   1 == omhoog
	 * 			   2 == links
	 * 			   3 == beneden
 	 */
	private int orientation;
	private int[] position = new int[2];
	
	public Police(GameBoard gameBoard){
		Random r = new Random();
		
		position['x'] = 0;
		position['y'] = 0;
		
		while(gameBoard.getObject(position['x'], position['y']) != null || (position['x'] == 0 && position['y'] == 0)) {
			// Randomly place police object on the gameboard
			position['x'] = r.nextInt(gameBoard.getWidth());
			position['y'] = r.nextInt(gameBoard.getHeight());
		}
		orientation = 0;
		
		gameBoard.addGameObject(this, this.position['x'], this.position['y']);
	}
	
	public String getImageId() {
		if(orientation == 0){
			return POLICE_IMAGE_R;
		}else if(orientation == 1){
			return POLICE_IMAGE_U;
		}else if(orientation == 2){
			return POLICE_IMAGE_L;
		}else{
			return POLICE_IMAGE_D;
		}
		
	
	}

	public void onTouched(GameBoard gameBoard) {
		Log.i("ONZINNIGE METHOD", "ONZINNIGE METHOD");
	}
	
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
		
		/*  */
	
		
		
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
		}
		
		// Move wombat to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}
	
	public void setOrientation(){// set in gesture
		
	}

}
