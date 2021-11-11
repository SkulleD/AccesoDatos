import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XML_ej7 {
	NodeList peliculas;
	
	public void addAttribute(Document doc, String attr) {
		peliculas = doc.getElementsByTagName("pelicula");
	}

	public void removeFilm(Document doc) {
		peliculas = doc.getElementsByTagName("pelicula");
	}

	public static void main(String[] args) {
		XML_ej7 dom = new XML_ej7();
		XML_ej1 dom1 = new XML_ej1();
		String ruta = "file:///C://Users//AlvaroVila//Desktop//CLASE//AD - Acceso a Datos//Práctica//peliculas.xml";
		Document documento = dom1.creaArbol(ruta);
	}
}