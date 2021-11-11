import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML_ej4 {
	public void recorrecursivo(Document doc) {
		Node raiz;
		NodeList titulos = doc.getElementsByTagName("titulo");
		NodeList directores;
		NodeList nombres;
		NodeList apellidos;
		
		raiz = doc.getFirstChild();
		System.out.printf("%d %s", checkType(raiz), raiz.getNodeName());
		
		for (int i = 0; i < titulos.getLength(); i++) {
			System.out.printf("\t%d %s\n", checkType(titulos.item(i)) ,titulos.item(i).getNodeName());
		}
	}
	
	public int checkType(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			return 1;
		} else if (node.getNodeType() == Node.TEXT_NODE) {
			return 3;
		} else {
			return 8;
		}
	}
	
	public static void main(String[] args) {
		XML_ej4 dom = new XML_ej4();
		XML_ej1 dom1 = new XML_ej1();
		String ruta = "file:///C://Users//AlvaroVila//Desktop//CLASE//AD - Acceso a Datos//Práctica//peliculas.xml";
		Document documento = dom1.creaArbol(ruta);
		dom.recorrecursivo(documento);
	}
}