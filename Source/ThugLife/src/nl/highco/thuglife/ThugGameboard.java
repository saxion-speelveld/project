package nl.highco.thuglife;

import nl.saxion.act.playground.model.GameBoard;

public class ThugGameboard extends GameBoard {
	//private static final int BOARD_WIDTH = 20;
	//private static final int BOARD_HEIGHT = 20;
	
	
	public ThugGameboard(int width, int hight) {
		super(width, hight);
	}
	
	public void onEmptyTileClicked(int x, int y) {
	
	}
	
	public String getBackgroundImg(int mx, int my) {
		return null;
	}

}
