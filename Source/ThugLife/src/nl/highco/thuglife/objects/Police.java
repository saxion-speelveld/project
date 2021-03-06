package nl.highco.thuglife.objects;

import android.util.Log;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Police extends GameObject {
	public static int aiActive = 0; // 0 = stationairy
	// 1 = search for player
	// 2 = chase player

	public static final String POLICE_IMAGE_R = "police_r";
	public static final String POLICE_IMAGE_U = "police_u";
	public static final String POLICE_IMAGE_L = "police_l";
	public static final String POLICE_IMAGE_D = "police_d";
	/**
	 * orientation 0 == rechts 1 == omhoog 2 == links 3 == beneden
	 */
	private int orientation;

	public Police() {
		orientation = 0;
		Log.i("Police AI", "Placed on map with coords: (" + getPositionX()
				+ ", " + getPositionY() + ")");
	}

	public void setAI(int number) {
		aiActive = number;
	}

	public String getImageId() {
		if (orientation == 0) {
			return POLICE_IMAGE_R;
		} else if (orientation == 1) {
			return POLICE_IMAGE_U;
		} else if (orientation == 2) {
			return POLICE_IMAGE_L;
		} else {
			return POLICE_IMAGE_D;
		}

	}

	public void onTouched(GameBoard gameBoard) {
		Log.i("ONZINNIGE METHOD", "ONZINNIGE METHOD");
	}

	public Boolean doAi(GameBoard gameBoard) {
		Log.i("Police AI", "Searching for player..");
		Log.i("Police AI", "Checking surroundings");
		int myX = getPositionX();
		int myY = getPositionY();
		boolean playerFound = false;
		for (int x = (myX - 5); x < (myX + 5); x++) {
			for (int y = (myY - 5); y < (myY + 5); y++) {
				if (x >= 0 && y >= 0 && x < gameBoard.getWidth() && y < gameBoard.getHeight()) {
					if (gameBoard.getObject(x, y) != null) {
						Log.i("Police AI", "Object Found");
						GameObject player = gameBoard.getObject(x, y);
						/*
						 * orientation: x++ = 0 x-- = 2 y++ = 3 y-- = 1
						 */
						if (player instanceof Player) {
							playerFound = true;
							if (myX < x
									&& !(gameBoard.getObject(myX - 1, myY) instanceof Wall)) {
								Log.i("Police AI", "Object is at: (" + x + ", "
										+ y + ") myX < x");
								orientation = 0;
							} else if (myY > y
									&& !(gameBoard.getObject(myX, myY + 1) instanceof Wall)) {
								Log.i("Police AI", "Object is at: (" + x + ", "
										+ y + ") myY > y");
								orientation = 1;
							} else if (myX > x
									&& !(gameBoard.getObject(myX + 1, myY) instanceof Wall)) {
								Log.i("Police AI", "Object is at: (" + x + ", "
										+ y + ") myX > x");
								orientation = 2;
							} else {
								Log.i("Police AI", "Object is at: (" + x + ", "
										+ y + ")");
								orientation = 3;
							}
						}
					}
				}
			}
		}
		return playerFound;
	}

	public void onUpdate(GameBoard gameBoard) {
		Log.i("Police AI", "AI active");
		if (!doAi(gameBoard)) {
			orientation = (int) (Math.random() * 4);
		}
		int newPosX = getPositionX();
		int newPosY = getPositionY();

		if (orientation == 0) {
			newPosX = getPositionX() + 1;
			newPosY = getPositionY();
		} else if (orientation == 1) {
			newPosX = getPositionX();
			newPosY = getPositionY() - 1;
		} else if (orientation == 2) {
			newPosX = getPositionX() - 1;
			newPosY = getPositionY();
		} else if (orientation == 3) {
			newPosX = getPositionX();
			newPosY = getPositionY() + 1;
		}

		// Check if there is a object on the new position
		GameObject objectAtNewPos = gameBoard.getObject(newPosX, newPosY);
		if (objectAtNewPos != null) {

			if (objectAtNewPos instanceof Wall) {
				return;
			}
			if (objectAtNewPos instanceof Player) {
				Player player = (Player) gameBoard.getObject(newPosX, newPosY);
				player.setPlayerToDeadState();
				gameBoard.updateView();
			}
			if (objectAtNewPos instanceof Police) {
				return;
			}
		} else {
			// Move object
			gameBoard.moveObject(this, newPosX, newPosY);
		}
	}

}
