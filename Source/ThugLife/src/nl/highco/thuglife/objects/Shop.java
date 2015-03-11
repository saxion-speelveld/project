package nl.highco.thuglife.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Shop extends GameObject{
	public static final String SHOP_IMAGE = "shop";
	
	public String getImageId() {
		
		return SHOP_IMAGE;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {
			
	}

}
