import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML_ej3 {
	public void getPeliculas(Document doc) {
		NodeList peliculas = doc.getElementsByTagName("pelicula");

		Node directorNodo, nombre, apellido;

		for (int i = 0; i < peliculas.getLength(); i++) {
			NodeList titulo = doc.getElementsByTagName("titulo");
			NodeList director = doc.getElementsByTagName("director");
			NodeList nombres = doc.getElementsByTagName("nombre");
			NodeList apellidos = doc.getElementsByTagName("apellido");
			
			for (int j = 0; j < director.getLength() - 1; j++) {
				System.out.printf("Película: %s", titulo.item(j).getFirstChild().getNodeValue());
				System.out.printf("\nDirector: %s", nombres.item(j).getFirstChild().getNodeValue() + " ");
				System.out.println(apellidos.item(j).getFirstChild().getNodeValue());
				System.out.println("------------------");
			}
		}
		
//		for (int i = 0 ; i < director.getLength(); i++) {
//			nombre = director.item(i).getFirstChild();
//			
//			if (nombre.getNodeType() == Node.ELEMENT_NODE) {
//				System.out.println(nombre.getFirstChild().getNodeValue());
//			}
	}
	
	public static void main(String[] args) {
		XML_ej3 dom = new XML_ej3();
		XML_ej1 dom1 = new XML_ej1();
		String ruta = "file:///C://Users//AlvaroVila//Desktop//CLASE//AD - Acceso a Datos//Práctica//peliculas.xml";
		Document documento = dom1.creaArbol(ruta);
		dom.getPeliculas(documento);
	}
}
