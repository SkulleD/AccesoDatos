import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML_ej2 {
//	public String getDato(Node node, String nombre) {
//		Element aux = (Element)node;
//		NodeList lista = aux.getElementsByTagName(nombre);
//		
//		if (lista.getLength() > 0) {
//			return lista.item(0).getFirstChild().getNodeValue();
//		} else {
//			return null;
//		}
//	}
	
	public void getTitulo(Document doc) {
		NodeList titulos = doc.getElementsByTagName("titulo");
		
		for (int i = 0; i < titulos.getLength(); i++) {
			System.out.println("----------");
			//System.out.println(getDato(titulos.item(i), "u"));
			System.out.println(titulos.item(i).getFirstChild().getNodeValue());
		}
	}
	
	public static void main(String[] args) {
		XML_ej2 dom = new XML_ej2();
		XML_ej1 dom1 = new XML_ej1();
		String ruta = "file:///C://Users//AlvaroVila//Desktop//CLASE//AD - Acceso a Datos//Práctica//peliculas.xml";
		Document documento = dom1.creaArbol(ruta);
		dom.getTitulo(documento);
	}
}