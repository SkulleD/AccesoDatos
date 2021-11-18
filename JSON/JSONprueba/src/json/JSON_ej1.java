package json;

import java.util.Iterator;

public class JSON_ej1 {
	JSON_CreaArbol creaArbol = new JSON_CreaArbol();
	
	public void buscaLocalidad(String ciudad) {
		String enlace = "http://api.openweathermap.org/data/2.5/weather?q="+ciudad+",es&lang=es&APPID=a975f935caf274";
		creaArbol.readJSON(enlace);
		
		
		
		for (int i = 0; i < enlace.length(); i++) {
			
		}
	}

	public static void main(String[] args) {
		JSON_ej1 ej1 = new JSON_ej1();
		String ciudad = "vigo";
		ej1.buscaLocalidad(ciudad);
	}
}