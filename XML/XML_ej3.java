package xmlpackage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XML_ej3 {
	public static void muestraDirectores(Document doc) {
		Element padre;
		NodeList titulos = doc.getElementsByTagName("titulo");
		NodeList directores, nombres, apellidos;

		for (int i = 0; i < titulos.getLength(); i++) {
			padre = (Element) titulos.item(i).getParentNode();
			System.out.println("----------");
			System.out.println(
					"Título: " + titulos.item(i).getFirstChild().getNodeValue() + " | Género: " + padre.getAttribute("genero"));

			directores = padre.getElementsByTagName("director");
			nombres = padre.getElementsByTagName("nombre");
			apellidos = padre.getElementsByTagName("apellido");

			for (int j = 0; j < directores.getLength(); j++) {
				System.out.println("Nombre: " + nombres.item(j).getFirstChild().getNodeValue());
				System.out.println("Apellido/s: " + apellidos.item(j).getFirstChild().getNodeValue());
			}
		}
	}

	public static void main(String[] args) {
		XML_ej1 ej1 = new XML_ej1();
		String ruta = "C:\\peliculas.xml";
		Document doc = ej1.creaArbol(ruta);
		muestraDirectores(doc);
	}
}
