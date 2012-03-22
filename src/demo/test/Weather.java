package demo.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import demo.test.utils.DomParseWeather;
import demo.test.utils.WeatherModel;
import demo.test.utils.WeatherXml;

public class Weather extends Activity {
	Activity context = this;

	private EditText city;

	private Button button;

	private ListView listView;

	private Drawable[] images;

	private String[] dates;

	private String[] temperatures;

	private String[] conditions;

	private double[] low;

	private double[] high;

	private WeatherAdapter adapter;

	private ProgressDialog mypDialog;

	private static final String TAG = "Handler";

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				initData();
				adapter = new WeatherAdapter(context, images, dates,
						temperatures, conditions);
				listView.setAdapter(adapter);
				handler.sendMessage(handler.obtainMessage(2));
				break;
			case 2:
				mypDialog.dismiss();
				button.setClickable(true);
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);
		initView();
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mypDialog = ProgressDialog.show(context, "",
						"Loading. Please wait...", true, true);
				button.setClickable(false);
				//
				new Thread(new Runnable() {

					@Override
					public void run() {
						handler.post(new Runnable() {
							@Override
							public void run() {
								handler.sendMessage(handler.obtainMessage(1));
							}
						});
					}
				}).start();
			}
		});
	}

	private void initView() {
		city = (EditText) findViewById(R.id.city);
		button = (Button) findViewById(R.id.button);
		button.requestFocus();
		listView = (ListView) findViewById(R.id.list_weather);
	}

	private void initData() {
		String xmlStr = WeatherXml.getWeatherXml(city.getText().toString());
		Log.i(TAG, xmlStr);
		List<WeatherModel> ws = DomParseWeather.getWeatherFromXml(xmlStr);

		images = new Drawable[ws.size()];
		dates = new String[ws.size()];
		temperatures = new String[ws.size()];
		conditions = new String[ws.size()];
		low = new double[ws.size()];
		high = new double[ws.size()];

		WeatherModel w = null;
		for (int i = 0; i < ws.size(); i++) {
			w = ws.get(i);
			images[i] = loadImage(w.getImageUrl());
			dates[i] = w.getDay_of_week();
			conditions[i] = w.getCondition();
			low[i] = Integer.parseInt(w.getLow_temperature());
			high[i] = Integer.parseInt(w.getHigh_temperature());
		}
		dataShift();
	}

	private Drawable loadImage(String imageUrl) {
		try {
			return Drawable.createFromStream((InputStream) new URL(
					WeatherXml.HOST_ADDRESS + imageUrl).getContent(), "image");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void dataShift() {

		for (int i = 0; i < dates.length; i++) {
			if ("Mon".equals(dates[i])) {
				dates[i] = "星期一";

			} else if ("Tue".equals(dates[i])) {
				dates[i] = "星期二";

			} else if ("Wed".equals(dates[i])) {
				dates[i] = "星期三";

			} else if ("Thu".equals(dates[i])) {
				dates[i] = "星期四";

			} else if ("Fri".equals(dates[i])) {
				dates[i] = "星期五";

			} else if ("Sat".equals(dates[i])) {
				dates[i] = "星期六";

			} else if ("Sun".equals(dates[i])) {
				dates[i] = "星期日";
			}
		}

		for (int i = 0; i < conditions.length; i++) {
			if ("Chance of Rain".equals(conditions[i])) {
				conditions[i] = "可能有雨";
			} else if ("Clear".equals(conditions[i])) {
				conditions[i] = "晴";
			} else if ("Partly Sunny".equals(conditions[i])) {
				conditions[i] = "多云间晴";
			} else if ("Mostly Sunny".equals(conditions[i])) {
				conditions[i] = "晴间多云";
			} else if ("Fog".equals(conditions[i])) {
				conditions[i] = "雾";
			} else if ("Rain".equals(conditions[i])) {
				conditions[i] = "雨";
			} else if ("Chance of Storm".equals(conditions[i])) {
				conditions[i] = "可能有暴风雨";
			}
		}

		for (int i = 0; i < temperatures.length; i++) {
			Log.i(TAG, low[i] + "）））））））");
			low[i] = 5d / 9d * (low[i] - 32);
			Log.i(TAG, low[i] + "----------");
			high[i] = 5d / 9d * (high[i] - 32);
			Log.i(TAG, high[i] + "----------****");
			temperatures[i] = String.valueOf(low[i]).substring(0, 2) + " ~ "
					+ String.valueOf(high[i]).substring(0, 2);
		}
	}
}
