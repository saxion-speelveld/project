package nl.highco.thuglife.view;

import nl.highco.thuglife.MainActivity;
import nl.highco.thuglife.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GameOverFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.game_over_fragment, null);
		Button button = (Button) rootView.findViewById(R.id.returnToMainMenu);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity ac =(MainActivity) getActivity();
				ac.gotoMainMenu();
			}
		});
		return rootView;
		
	}
	
}
