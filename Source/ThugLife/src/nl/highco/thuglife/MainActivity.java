package nl.highco.thuglife;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import nl.highco.thuglife.shop.*;

public class MainActivity extends Activity{
	private ThugGame game;
	private ThugGameBoardView gameView;
	private TextView textView, textViewScoreG, textViewMoneyG, textViewMoneyS, textViewWietG, textViewWietS;
	private TextView textViewProgress;
	private ListView listView;
	private Button btnBackToMenu, buttonReset, backButton, btnStartResume;
	private Button Start;
	private Button sellButton;
	private SeekBar seekBarWiet;
	private LinearLayout mainMenu, layoutShop, layoutGame;
	private ArrayList<ShopItem> shopItems;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Layouts
		mainMenu = (LinearLayout) findViewById(R.id.mainMenu);
		layoutGame = (LinearLayout) findViewById(R.id.gameView);
		layoutShop = (LinearLayout) findViewById(R.id.shopView);

		// Game
		gameView = (ThugGameBoardView) findViewById(R.id.game);
		game = new ThugGame(this);

		textView = (TextView) findViewById(R.id.textViewCost);
		textViewScoreG = (TextView) findViewById(R.id.textViewScoreG);
		textViewMoneyG = (TextView) findViewById(R.id.textViewMoneyG);
		textViewMoneyS = (TextView) findViewById(R.id.textViewMoneyS);
		textViewWietG = (TextView) findViewById(R.id.textViewWietG);
		textViewWietS = (TextView) findViewById(R.id.textViewWietS);
		textViewProgress = (TextView) findViewById(R.id.textViewProgress);

		btnBackToMenu = (Button) findViewById(R.id.btnBackToMenu);
		btnBackToMenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				game.stopTimers();
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
		
		//shop items/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		shopItems = new ArrayList<ShopItem>();
		shopItems.add(new ShopItem("wiet", "speed +1", 20, 1));
		shopItems.add(new ShopItem("was", "speed +3", 60, 5));
		shopItems.add(new ShopItem("wasf", "speed +3", 60, 11));
		shopItems.add(new ShopItem("wasw", "speed +3", 60,6));
		
		//shop///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ShopAdapter adapter = new ShopAdapter(this, 0, shopItems, game);
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		
		
		backButton = (Button) findViewById(R.id.backButton);
		backButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				gotoGameView();
				btnStartResume.setVisibility(View.VISIBLE);
			}
		});
		
		sellButton = (Button) findViewById(R.id.sellButton);
		sellButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int wietToSell = seekBarWiet.getProgress();
				int sellPrice = 5;
				
				game.deductWiet(wietToSell);
				game.addMoney(sellPrice * wietToSell);
				// progress naar 0
				seekBarWiet.setProgress(0);
				seekBarWiet.setMax(game.getWiet());
				textViewProgress.setText("0");
				// listView refresh
				listView.invalidateViews();
			}
		});
		
		seekBarWiet = (SeekBar) findViewById(R.id.seekBarWiet);
		seekBarWiet.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
			
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(fromUser){
					textViewProgress.setText(""+progress);
				}
				
			}
		});
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Start = (Button) findViewById(R.id.startButton);
		Start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				gotoGameView();
				game.startPoliceTimer();
				game.startPlayerTimer(250);
			}
		});

		btnStartResume = (Button) findViewById(R.id.btnStartResume);
		btnStartResume.setVisibility(View.GONE);
		btnStartResume.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// police
				game.startPoliceTimer();
				// player
				int playerSpeed = 250;
				for(ShopItem s : shopItems){
					if(s.getBought()){
						playerSpeed -= s.getBonus();
					}
					
				}
				game.startPlayerTimer(playerSpeed);
				
				btnStartResume.setVisibility(View.GONE);
				btnStartResume.setText("Hervatten");
			}
		});

		gotoMainMenu();
	}

	public ThugGameBoardView getGameBoardView() {
		return gameView;
	}
	//label methods//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setGameState(String s) {
		textView.setText(s + "");
	}
	
	public void updateScoreLabel(){
		textViewScoreG.setText("Score: "+ game.getScore());
	}
	
	public void updateWietLabels(){
		textViewWietG.setText("Wiet: " + game.getWiet());
		textViewWietS.setText("Wiet: " + game.getWiet());
	}
	
	public void updateMoneyLabels(){
		textViewMoneyG.setText("Money: "+ game.getMoney());
		textViewMoneyS.setText("Money: "+ game.getMoney());
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	

	public void gotoShopView() {
		seekBarWiet.setMax(game.getWiet());
		mainMenu.setVisibility(View.GONE);
		layoutGame.setVisibility(View.GONE);
		layoutShop.setVisibility(View.VISIBLE);
	}

	public void gotoGameView() {
		mainMenu.setVisibility(View.GONE);
		layoutShop.setVisibility(View.GONE);
		layoutGame.setVisibility(View.VISIBLE);
	}

	public void gotoMainMenu() {
		mainMenu.setVisibility(View.VISIBLE);
		layoutShop.setVisibility(View.GONE);
		layoutGame.setVisibility(View.GONE);
	}
	
	
	
}
