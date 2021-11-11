import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML_ej3 {
	public void getPeliculas(Document doc) {
		NodeList titulos = doc.getElementsByTagName("titulo");
		NodeList directores;
		NodeList nombres;
		NodeList apellidos;
		Element padre;
		
		for (int i = 0; i < titulos.getLength(); i++) {
			padre = (Element)titulos.item(i).getParentNode();
			System.out.println("-----------------");
			System.out.printf("Título: %s \n", titulos.item(i).getFirstChild().getNodeValue());
			
			directores = padre.getElementsByTagName("director");
			nombres = padre.getElementsByTagName("nombre");
			apellidos = padre.getElementsByTagName("apellido");
			
			for (int j = 0; j < directores.getLength(); j++) {
				System.out.printf("Director/a: %s %s\n", nombres.item(j).getFirstChild().getNodeValue(), apellidos.item(j).getFirstChild().getNodeValue());
			}
		}
	}
	
	public static void main(String[] args) {
		XML_ej3 dom = new XML_ej3();
		XML_ej1 dom1 = new XML_ej1();
		String ruta = "file:///C://Users//AlvaroVila//Desktop//CLASE//AD - Acceso a Datos//Práctica//peliculas.xml";
		Document documento = dom1.creaArbol(ruta);
		dom.getPeliculas(documento);
	}
}