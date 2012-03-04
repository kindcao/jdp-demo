package demo.test.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DomParseWeather {

	public static List<WeatherModel> getWeatherFromXml(String xmlStr) {
		List<WeatherModel> weathers = new ArrayList<WeatherModel>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource(new StringReader(xmlStr));
			Document document = builder.parse(inputSource);

			// xml_api_reply
			Element root = document.getDocumentElement();

			// weather.....
			NodeList nodeChildren1 = root.getChildNodes();

			for (int i = 0; i < nodeChildren1.getLength(); i++) {

				// weather
				Node nodeChild1 = nodeChildren1.item(i);

				// forecast_conditions....
				NodeList nodeChildren2 = nodeChild1.getChildNodes();

				for (int j = 0; j < nodeChildren2.getLength(); j++) {

					// forecast_conditions
					Node nodeChild2 = nodeChildren2.item(j);

					if ("forecast_conditions".equals(nodeChild2.getNodeName())) {
						WeatherModel w = new WeatherModel();
						;

						// high..low....condition......
						NodeList nodeChildren3 = nodeChild2.getChildNodes();

						for (int k = 0; k < nodeChildren3.getLength(); k++) {

							// high. low. condition..
							Node nodeChild3 = nodeChildren3.item(k);

							if ("day_of_week".equals(nodeChild3.getNodeName())) {
								String day_of_week = nodeChild3.getAttributes()
										.item(0).getNodeValue();
								w.setDay_of_week(day_of_week);

								// Log.i("TAG", day_of_week);

							} else if ("low".equals(nodeChild3.getNodeName())) {
								String low = nodeChild3.getAttributes().item(0)
										.getNodeValue();
								w.setLow_temperature(low);

								// Log.i("TAG", low);

							} else if ("high".equals(nodeChild3.getNodeName())) {
								String high = nodeChild3.getAttributes()
										.item(0).getNodeValue();
								w.setHigh_temperature(high);

								// Log.i("TAG", high);

							} else if ("icon".equals(nodeChild3.getNodeName())) {
								String icon = nodeChild3.getAttributes()
										.item(0).getNodeValue();
								w.setImageUrl(icon);

								// Log.i("TAG", icon);

							} else if ("condition".equals(nodeChild3
									.getNodeName())) {
								String condition = nodeChild3.getAttributes()
										.item(0).getNodeValue();
								w.setCondition(condition);

								// Log.i("TAG", condition);
							}

						}

						weathers.add(w);
					}

				}

			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return weathers;

	}

}
