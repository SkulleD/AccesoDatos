import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML_ej5 {
	public void conMasDirectores(Document doc, int n) {
		NodeList peliculas = doc.getElementsByTagName("pelicula");
		NodeList directores;
		NodeList titulos;
		
		for (int i = 0; i < peliculas.getLength(); i++) {
			directores = ((Element)peliculas.item(i)).getElementsByTagName("director");
			
			if (directores.getLength() >= n) {
				titulos = ((Element)peliculas.item(i)).getElementsByTagName("titulo");
				
				if (titulos.getLength() > 0) {
					System.out.println(titulos.item(0).getFirstChild().getNodeValue());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		XML_ej5 dom = new XML_ej5();
		XML_ej1 dom1 = new XML_ej1();
		String ruta = "file:///C://Users//AlvaroVila//Desktop//CLASE//AD - Acceso a Datos//Práctica//peliculas.xml";
		Document documento = dom1.creaArbol(ruta);
		dom.conMasDirectores(documento, 2);
	}
}