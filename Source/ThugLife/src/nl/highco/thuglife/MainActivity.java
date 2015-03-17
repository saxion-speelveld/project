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
	private Button btnBackToMenu, buttonReset, backButton, btnStartResume;
	private Button Start;
	private LinearLayout mainMenu, layoutShop,layoutGame;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//linken voor contol visible state
		mainMenu = (LinearLayout) findViewById(R.id.mainMenu);
		layoutGame = (LinearLayout) findViewById(R.id.gameView);
		layoutShop = (LinearLayout) findViewById(R.id.shopView);
		//
		
		gameView = (ThugGameBoardView) findViewById(R.id.game);
		
		game = new ThugGame(this);
		
		textView = (TextView) findViewById(R.id.textView1);
		
		btnBackToMenu = (Button) findViewById(R.id.btnBackToMenu);
		btnBackToMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				game.stopTimer();
				gotoMainMenu();
			}
		});
		
		buttonReset = (Button) findViewById(R.id.buttonReset);
		buttonReset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				game.reset();
				btnStartResume.setText("Start");
				btnStartResume.setVisibility(View.VISIBLE);				
			}
		});
		
		backButton = (Button) findViewById(R.id.backButton);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoGameView();
				btnStartResume.setVisibility(View.VISIBLE);
			}
		});
		
		Start = (Button) findViewById(R.id.startButton);
		Start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoGameView();
				game.startTimer();
			}
		});
		
		btnStartResume = (Button) findViewById(R.id.btnStartResume);
		btnStartResume.setVisibility(View.GONE);
		btnStartResume.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				game.startTimer();
				btnStartResume.setVisibility(View.GONE);
				btnStartResume.setText("Hervatten");
			}
		});
		
		gotoMainMenu();
	}
	
	
	
	public ThugGameBoardView getGameBoardView() {
		return gameView;
	}
	
	public void setText(String s){
		textView.setText(s+"");
	}
	
	public void gotoShopView(){
		mainMenu.setVisibility(View.GONE);
		layoutGame.setVisibility(View.GONE);
		layoutShop.setVisibility(View.VISIBLE);
	}
	
	public void gotoGameView(){
		mainMenu.setVisibility(View.GONE);
		layoutShop.setVisibility(View.GONE);
		layoutGame.setVisibility(View.VISIBLE);
	}
	
	public void gotoMainMenu(){
		mainMenu.setVisibility(View.VISIBLE);
		layoutShop.setVisibility(View.GONE);
		layoutGame.setVisibility(View.GONE);
	}
}
