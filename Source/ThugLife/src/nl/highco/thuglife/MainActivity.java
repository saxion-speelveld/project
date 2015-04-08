package nl.highco.thuglife;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import nl.highco.thuglife.shop.*;
import nl.highco.thuglife.view.GameOverFragment;

public class MainActivity extends Activity{
	private ThugGame game;
	private ThugGameBoardView gameView;
	private TextView textView, textViewScoreG, textViewMoneyG, textViewMoneyS, textViewWietG, textViewWietS;
	private ListView listView;
	private Button btnBackToMenu, buttonReset, backButton, btnStartResume, buttonReturnM;
	private Button Start, helpButton;
	private Button sellButton;
	private LinearLayout mainMenu, layoutShop, layoutGame, layoutHelp;
	private ArrayList<ShopItem> shopItems;
	private FragmentManager fragmentManager;
	private GameOverFragment fragment;
	private EditText editTextNumberSell; 
	private ShopAdapter adapter; 
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Layouts
		mainMenu = (LinearLayout) findViewById(R.id.mainMenu);
		layoutGame = (LinearLayout) findViewById(R.id.gameView);
		layoutShop = (LinearLayout) findViewById(R.id.shopView);
		layoutHelp = (LinearLayout) findViewById(R.id.helpView);

		// Game
		gameView = (ThugGameBoardView) findViewById(R.id.game);
		game = new ThugGame(this);

		textView = (TextView) findViewById(R.id.textViewCost);
		textViewScoreG = (TextView) findViewById(R.id.textViewScoreG);
		textViewMoneyG = (TextView) findViewById(R.id.textViewMoneyG);
		textViewMoneyS = (TextView) findViewById(R.id.textViewMoneyS);
		textViewWietG = (TextView) findViewById(R.id.textViewWietG);
		textViewWietS = (TextView) findViewById(R.id.textViewWietS);

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
		shopItems.add(new ShopItem("wiet (rolled)", "speed +1", 2, 4));
		shopItems.add(new ShopItem("hash", "speed +2", 4, 16));
		shopItems.add(new ShopItem("cocaine", "speed +3", 6, 36));
		shopItems.add(new ShopItem("heroine", "speed +4", 8, 64));
		shopItems.add(new ShopItem("speed", "speed +5", 10, 100));
		shopItems.add(new ShopItem("speed en coke", "speed +10", 20, 200));
		shopItems.add(new ShopItem("speed speciaal", "speed +15", 30, 500));
		shopItems.add(new ShopItem("speed speciaal en coke", "speed +20", 40,800));
		
		//shop///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		adapter = new ShopAdapter(this, 0, shopItems, game);
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
				int sellPrice = 5;
				
				game.deductWiet(Integer.parseInt(""+editTextNumberSell.getText()));
				game.addMoney(sellPrice * (Integer.parseInt(""+editTextNumberSell.getText())));
				// progress naar 0
				editTextNumberSell.setText(0+"");
				// listView refresh
				adapter.notifyDataSetChanged();
			}
		});
		
		helpButton = (Button) findViewById(R.id.helpButton);
		helpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoHelp();
			}
		});
		
		buttonReturnM = (Button) findViewById(R.id.buttonReturnM);
		buttonReturnM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoMainMenu();
			}
		});
		////////////////////////////////
		editTextNumberSell = (EditText) findViewById(R.id.editTextNumberSell);
		editTextNumberSell.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(s.toString().compareTo("") == 0){
					editTextNumberSell.setText(""+0);
					return;
				}
				if(Integer.parseInt(s + "") > game.getWiet()){
					editTextNumberSell.setText(game.getWiet()+"");
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
		fragmentManager = getFragmentManager();
		fragment = new GameOverFragment();
		gotoMainMenu();
	}
	
	public ThugGameBoardView getGameBoardView() {
		return gameView;
	}
	//shop reset
	public void resetShop(){
		for(ShopItem s : shopItems){
			s.setBought(false);
		}
	}
	
	public void gotoGameOverScreen(){
		fragmentManager.beginTransaction().replace(android.R.id.content,fragment ).commit();
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
		editTextNumberSell.setText(0+"");
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
		fragmentManager.beginTransaction().remove(fragment).commit();
		game.reset();
		mainMenu.setVisibility(View.VISIBLE);
		layoutShop.setVisibility(View.GONE);
		layoutGame.setVisibility(View.GONE);
		layoutHelp.setVisibility(View.GONE);
	}
	
	public void gotoHelp(){
		mainMenu.setVisibility(View.GONE);
		layoutHelp.setVisibility(View.VISIBLE);
	}
	
	
	
}
