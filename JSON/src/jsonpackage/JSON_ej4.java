package jsonpackage;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class JSON_ej4 {
	static JSONCreaArbol arbol = new JSONCreaArbol();

	public static int devuelveID(String ciudad) {
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + ciudad + ",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0";
		return arbol.leeJSON(url).asJsonObject().getInt("id");
	}

	public static void main(String[] args) {
		String ciudad = "o carballino";
		System.out.printf("ID: %d", devuelveID(ciudad));
	}
}