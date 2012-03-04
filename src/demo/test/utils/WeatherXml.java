package demo.test.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class WeatherXml {

	private static String url = "http://www.google.com/ig/api?weather=";

	public static String getWeatherXml(String weather) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(url + weather);
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				httpGet.abort();
			} else {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			Log.e("WeatherReport", "Failed to get weather", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
}
