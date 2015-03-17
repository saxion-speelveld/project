package nl.highco.thuglife;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ThugGame game;
	private ThugGameBoardView gameView;
	private TextView textView;
	private Button buttonStart, buttonStop, backButton;
	private LinearLayout layoutShop,layoutGame;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//linken voor contol visible state
		layoutGame = (LinearLayout) findViewById(R.id.gameView);
		layoutShop = (LinearLayout) findViewById(R.id.shopView);
		//
		
		gameView = (ThugGameBoardView) findViewById(R.id.game);
		
		game = new ThugGame(this);
		
		textView = (TextView) findViewById(R.id.textView1);
		
		buttonStart= (Button) findViewById(R.id.buttonStart);
		buttonStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				game.startTimer();
				
			}
		});
		
		buttonStop = (Button) findViewById(R.id.buttonStop);
		buttonStop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				game.stopTimer();
				
			}
		});
		
		backButton = (Button) findViewById(R.id.backButton);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoGameView();
			}
		});
		
	}
	
	
	
	public ThugGameBoardView getGameBoardView() {
		return gameView;
	}
	
	public void setText(String s){
		textView.setText(s+"");
	}
	
	public void gotoShopView(){
		layoutGame.setVisibility(View.GONE);
		layoutShop.setVisibility(View.VISIBLE);
	}
	
	public void gotoGameView(){
		layoutShop.setVisibility(View.GONE);
		layoutGame.setVisibility(View.VISIBLE);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
	//onodig  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
