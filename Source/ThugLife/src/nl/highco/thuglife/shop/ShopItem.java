package nl.highco.thuglife.shop;

public class ShopItem {
	private String description, bonusDescription;
	private int bonus;
	private boolean bought;
	private int cost;
	
	
	public ShopItem(String description, String bonusDescription, int bonus, int cost){
		this.description = description;
		this.bonusDescription = bonusDescription;
		this.bonus = bonus;
		bought = false;
		if(cost == 0){
			cost = 1;
		}else{
			this.cost = cost;
		}
	}
	
	public boolean getBought(){
		return bought;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getBonusDescription(){
		return bonusDescription;
	}
	
	public int getBonus(){
		return bonus;
	}
	
	public int getCost(){
		return cost;
	}
	
	public void setBought(boolean b){
		bought = b;
	}
	
	
}
