package demo.test.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * HttpPost工具�?
 * 
 * @author wanglin(lin3.wang@changhong.com) 2011-8-8下午02:31:37
 */
@SuppressWarnings({ "unchecked", "unused" })
public class HttpPostUtil {

	/**
	 * 通过HTTP POST的方式传递数�?
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String httpPostData(String url, Map<String, String> param)
			throws ClientProtocolException, IOException {
		HttpPost request = makeHttpPost(param, url);
		HttpClient client = new DefaultHttpClient();
		ResponseHandler<String> reshandler = new BasicResponseHandler();
		String result = client.execute(request, reshandler);
		return result;
	}

	private HttpEntity makeEntity(Vector<NameValuePair> nameValue) {
		HttpEntity result = null;
		try {
			result = new UrlEncodedFormEntity(nameValue, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	private HttpPost makeHttpPost(Map<String, String> param, String url) {
		HttpPost request = new HttpPost(url);
		Vector<NameValuePair> nameValue = new Vector<NameValuePair>();

		if (param != null) {
			Set set = param.keySet();
			Iterator e = set.iterator();
			int cnt = 0;
			while (e.hasNext()) {
				String name = (String) e.next();
				String value = (String) param.get(name);
				nameValue.add(new BasicNameValuePair(name, value));
			}
		}
		request.setEntity(makeEntity(nameValue));
		return request;
	}

	public Document getDocument(String xml) {
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(new String(xml));
			InputSource is = new InputSource(sr);
			doc = db.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public NodeList getElementsByTagNameNodeList(Document doc, String nodeName) {
		NodeList result = null;
		if (doc != null) {
			result = doc.getElementsByTagName(nodeName);
		}
		return result;
	}

	public HashMap getChildHashMap(Document doc, String nodeName) {
		HashMap resultData = new HashMap();
		NodeList nodeList = getElementsByTagNameNodeList(doc, nodeName);
		if (nodeList.getLength() > 0) {
			// resultData = ;
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				for (int j = 0; j < element.getChildNodes().getLength(); j++) {
					if (!"#text".trim().equals(
							element.getChildNodes().item(j).getNodeName())) {
						if (element.getChildNodes().item(j).getFirstChild() != null) {
							String s = element.getChildNodes().item(j)
									.getFirstChild().getNodeValue();

							resultData.put(element.getChildNodes().item(j)
									.getNodeName(), element.getChildNodes()
									.item(j).getFirstChild().getNodeValue());
						}
					}
				}
			}
		}
		return resultData;
	}

	public HashMap getChildXmlHashMap(String xml, String tagName)
			throws IOException {
		HashMap resultData = null;
		Document doc = getDocument(xml);
		resultData = getChildHashMap(doc, tagName);

		return resultData;
	}

	ArrayList<Object> resultList;

	public void setResult(ArrayList<Object> resultList) {
		this.resultList = resultList;
	}

	/**
	 * 
	 * @param pageUrl
	 * @param tagName
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public HashMap httpPostGetChild(String pageUrl, String tagName,
			Map<String, String> param) throws IOException {
		HashMap resultData = null;
		String xml = httpPostData(pageUrl, param);
		resultData = getChildXmlHashMap(xml, tagName);

		return resultData;
	}
}
