package jsonpackage;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class JSON_ej4 {
	JSONCreaArbol arbol = new JSONCreaArbol();
	int id = 340343;
	String url = "http://api.openweathermap.org/data/2.5/weather?" + id + "=3105976&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0";
	JsonObject tiempo;
	
	
	private JsonObject devuelveID(String url) {
		return arbol.leeJSON(url).asJsonObject();;
	}
	
	public static void main(String[] args) {
		JSON_ej4 ej4 = new JSON_ej4();
		ej4.devuelveID(ej4.url);
	}
}
