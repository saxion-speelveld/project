package nl.highco.thuglife;

import nl.saxion.act.playground.model.GameBoard;

public class ThugGameboard extends GameBoard {
	
	public ThugGameboard(int width, int hight) {
		super(width, hight);
	}
	
	public void onEmptyTileClicked(int x, int y) {
	
	}
	
	public String getBackgroundImg(int mx, int my) {
		return null;
	}

}
