package nl.highco.thuglife.shop;

import java.util.List;

import nl.highco.thuglife.*;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopAdapter extends ArrayAdapter<ShopItem>{
	private ThugGame game;
	private LayoutInflater inflater;
	
	
	public ShopAdapter(Context context, int resource, List<ShopItem> objects, ThugGame game) {
		super(context, resource, objects);
		this.game = game;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public View getView (int position, View convertView, ViewGroup parent){
		
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.shop_item, null);
		}
		
		ShopItem item = super.getItem(position);
		
		ImageView itemImage = (ImageView) convertView.findViewById(R.id.itemImage);
		TextView descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);
		TextView bonusTextView = (TextView) convertView.findViewById(R.id.bonusTextView);
		TextView boughtTextView = (TextView) convertView.findViewById(R.id.boughtTextView);
		TextView textViewCost = (TextView) convertView.findViewById(R.id.textViewCost);
		Button buyButton = (Button) convertView.findViewById(R.id.buyButton);
		
		
		descriptionTextView.setText(item.getDescription()+"");
		textViewCost.setText("Cost: " + item.getCost());
		bonusTextView.setText("bonus :" + item.getBonus());
		boughtTextView.setText("gekocht :" + item.getBought()+"");
		buyButton.setOnClickListener(new onBuyButtonClickListener(game, item));
		buyButton.setEnabled(true);
		
		if(item.getBought() == true){
			buyButton.setEnabled(false);
		}
		
		if(item.getCost() > game.getMoney()){
			if(!item.getBought()){
				buyButton.setEnabled(false);
				textViewCost.setText("Cost: " + item.getCost() +"     niet genoeg geld");
			}
		}
		
		
		return convertView;
		
	}
	
	private class onBuyButtonClickListener implements OnClickListener{
		ShopItem item;
		ThugGame game;
		
		public onBuyButtonClickListener(ThugGame game, ShopItem item){
			this.game = game;
			this.item = item;
		}
		
		public void onClick(View v) {
			
			Log.i("shop", "onclick"+item.getDescription());
			item.setBought(true);
			game.deductMoney(item.getCost());
			notifyDataSetChanged();
			
		}
	
	}

}
