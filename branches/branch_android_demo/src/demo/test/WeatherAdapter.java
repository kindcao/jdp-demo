package demo.test;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter {

	Activity mContext;

	private Drawable[] images;

	private String[] dates;

	private String[] temperatures;

	private String[] conditions;

	public WeatherAdapter(Activity c, Drawable[] images, String[] dates,
			String[] temperatures, String[] conditions) {
		mContext = c;
		this.images = images;
		this.dates = dates;
		this.temperatures = temperatures;
		this.conditions = conditions;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = mContext.getLayoutInflater();
			convertView = inflater.inflate(R.layout.weather_list_row, null);

			holder = new ViewHolder();

			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.date = (TextView) convertView.findViewById(R.id.day_of_week);
			holder.temperature = (TextView) convertView
					.findViewById(R.id.temperature);
			holder.condition = (TextView) convertView
					.findViewById(R.id.condition);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.image.setImageDrawable(images[position]);
		holder.date.setText(dates[position]);
		holder.temperature.setText(temperatures[position]);
		holder.condition.setText(conditions[position]);

		return convertView;
	}

	final class ViewHolder {
		ImageView image;
		TextView date;
		TextView temperature;
		TextView condition;
	}

	@Override
	public int getCount() {
		return dates.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
