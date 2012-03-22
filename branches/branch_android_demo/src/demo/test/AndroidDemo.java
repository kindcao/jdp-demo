package demo.test;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AndroidDemo extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] demoitems = getResources().getStringArray(R.array.demoitems);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.main, demoitems));
		//
		getListView().setTextFilterEnabled(true);		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(getApplicationContext(), SayHello.class));
			break;
		case 1:
			startActivity(new Intent(getApplicationContext(), Link.class));
			break;
		case 2:
			startActivity(new Intent(getApplicationContext(), Map.class));
			break;
		case 3:
			startActivity(new Intent(getApplicationContext(), Weather.class));
			break;

		default:
			break;
		}

	}
}