package nl.highco.thuglife.shop;

import java.util.List;

import nl.highco.thuglife.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopAdapter extends ArrayAdapter<ShopItem>{
	private ThugGame game;
	private LayoutInflater inflater;
	private ShopItem item;
	
	public ShopAdapter(Context context, int resource, List<ShopItem> objects, ThugGame game) {
		super(context, resource, objects);
		this.game = game;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public View getView (int position, View convertView, ViewGroup parent){
		item = super.getItem(position);
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.shop_item, null);
		}
		
		ImageView itemImage = (ImageView) convertView.findViewById(R.id.itemImage);
		TextView descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);
		TextView bonusTextView = (TextView) convertView.findViewById(R.id.bonusTextView);
		TextView boughtTextView = (TextView) convertView.findViewById(R.id.boughtTextView);
		Button buyButton = (Button) convertView.findViewById(R.id.buyButton);
		
		descriptionTextView.setText(item.getDescription()+"");
		bonusTextView.setText(item.getBonus()+"");
		boughtTextView.setText(item.getBought()+"");
		
		if(item.getBought()){
			buyButton.setEnabled(false);
		}
		
		buyButton.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				item.setBought(true);
				
			}
		});
		
		return convertView;
		
	}

}
