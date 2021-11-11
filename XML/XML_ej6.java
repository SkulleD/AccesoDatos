import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XML_ej6 {
	public void getGeneroPeli(Document doc) {
		ArrayList<String> generos = new ArrayList<>();
		NodeList peliculas = doc.getElementsByTagName("pelicula");
		String genero;
		Element peli;
		
		for (int i = 0; i < peliculas.getLength(); i++) {
			peli = (Element)peliculas.item(i);
			genero = peli.getAttribute("genero");
			
			if (!generos.contains(genero)) {
				generos.add(genero);
			}
		}
		
		System.out.printf("Nº de géneros de películas: %d\n\n", generos.size()); 
		
		for (String peliGenero : generos) {
			System.out.println(peliGenero);
		}
	}
	
	public static void main(String[] args) {
		XML_ej6 dom = new XML_ej6();
		XML_ej1 dom1 = new XML_ej1();
		String ruta = "file:///C://Users//AlvaroVila//Desktop//CLASE//AD - Acceso a Datos//Práctica//peliculas.xml";
		Document documento = dom1.creaArbol(ruta);
		dom.getGeneroPeli(documento);
	}
}