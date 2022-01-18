import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RepasoDOM2 {
	public static Document creaArbol(String ruta) {
		Document doc = null;
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			factoria.setIgnoringComments(true);
			DocumentBuilder builder = factoria.newDocumentBuilder();
			doc = builder.parse(ruta);
			System.out.println("-!ÁRBOL CREADO!-");
		} catch (Exception e) {
			System.out.println("Error generando el árbol DOM: " + e.getMessage());
		}
		return doc;
	}

	public static void muestraID(Document doc) {
		NodeList raiz = doc.getElementsByTagName("data");
		Element datos;
		NodeList circuitID;

		datos = (Element) raiz.item(0);
		circuitID = datos.getElementsByTagName("circuitId");

		for (int i = 0; i < circuitID.getLength(); i++) {
			System.out.println("Circuit ID " + (i + 1) + ": " + circuitID.item(i).getFirstChild().getNodeValue());
		}
	}

	public static void muestraInfoRace(Document doc) {
		NodeList carrera = doc.getElementsByTagName("Race");
		Element datos;
		NodeList country;
		NodeList latitude;
		NodeList longitude;
		NodeList contruct;
		NodeList contructName;

		for (int i = 0; i < carrera.getLength(); i++) {
			datos = (Element) carrera.item(i);
			country = datos.getElementsByTagName("country");
			latitude = datos.getElementsByTagName("lat");
			longitude = datos.getElementsByTagName("long");

			if (country.getLength() < 0) System.out.printf("Country: %s\n", country.item(0).getFirstChild().getNodeValue());
			if (latitude.getLength() < 0)System.out.printf("Lat: %s\n", latitude.item(0).getFirstChild().getNodeValue());
			if (longitude.getLength() < 0)System.out.printf("Long: %s\n", longitude.item(0).getFirstChild().getNodeValue());
						
			contruct = datos.getElementsByTagName("Constructor");
			for (int k = 0; k < contruct.getLength(); k++) {
				datos = (Element) contruct.item(k);
				contructName = datos.getElementsByTagName("name");
				for (int l = 0; l < contructName.getLength(); l++) {
					System.out.println("Name: " + contructName.item(l).getFirstChild().getNodeValue());
				}
			}
		}
	}

	public static void main(String[] args) {
		String ruta = "C:\\Carreras.xml";
		Document doc = creaArbol(ruta);
		// muestraID(doc);
		muestraInfoRace(doc);
	}
}