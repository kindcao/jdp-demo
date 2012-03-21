package demo.test.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class WeatherXml {

	public static final String HOST_ADDRESS = "http://www.google.com";

	private static final String URL = HOST_ADDRESS + "/ig/api?weather=";

	private static final int REQUEST_TIMEOUT = 15 * 1000;// 设置请求超时10秒钟

	private static final int SO_TIMEOUT = 15 * 1000; // 设置等待数据超时时间10秒钟

	public static String getWeatherXml(String weather) {
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		//
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(URL + weather);
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
		return "";
	}
}
