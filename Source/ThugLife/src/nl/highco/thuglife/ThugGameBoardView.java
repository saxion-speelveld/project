package nl.highco.thuglife;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import nl.highco.thuglife.R;
import nl.highco.thuglife.objects.Player;
import nl.highco.thuglife.objects.Shop;
import nl.highco.thuglife.objects.Wall;
import nl.saxion.act.playground.view.GameBoardView;
import nl.saxion.act.playground.view.SpriteCache;


public class ThugGameBoardView extends GameBoardView{
		private static final String TAG = "GameView";

		/**
		 * Constructor.
		 */
		public ThugGameBoardView(Context context, AttributeSet attrs) {
			super(context, attrs);
			initGameView();
		}

		/**
		 * Constructor.
		 */
		public ThugGameBoardView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initGameView();
		}

		/**
		 * Loads all images that will be used for the game.
		 */
		private void initGameView() {
			Log.d(TAG, "Loading all images");

			SpriteCache spriteCache = SpriteCache.getInstance(); 
			spriteCache.setContext(this.getContext());		

			// Load the 'empty' cell bitmap and tell the tile view that this is the
			// image to use for cells without GameObject
			spriteCache.loadTile("empty", R.drawable.cell);
			setEmptyTile("empty");

			// Load the images for the GameObjects
			spriteCache.loadTile(Player.PLAYER_IMAGE_R, R.drawable.player_r);
			spriteCache.loadTile(Player.PLAYER_IMAGE_U, R.drawable.player_u);
			spriteCache.loadTile(Player.PLAYER_IMAGE_D, R.drawable.player_d);
			spriteCache.loadTile(Player.PLAYER_IMAGE_L, R.drawable.player_l);
			spriteCache.loadTile(Wall.WALL_IMAGE, R.drawable.wall);
			spriteCache.loadTile(Shop.SHOP_IMAGE, R.drawable.shop);
		}
}
