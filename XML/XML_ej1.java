import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XML_ej1 {
	public Document creaArbol(String ruta) {
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

	public static void main(String[] args) {
		XML_ej1 dom = new XML_ej1();
		String ruta = "file:///C:\\Users\\AlvaroVila\\Desktop\\CLASE\\AD - Acceso a Datos\\Práctica\\peliculas.xml";
		Document documento = dom.creaArbol(ruta);
	}
}