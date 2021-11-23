package jsonpackage;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class JSON_ej1 {
	JSONCreaArbol arbol = new JSONCreaArbol();
	
	private JsonObject devuelveTiempo(String ciudad) {
		String url ="http://api.openweathermap.org/data/2.5/weather?q=" + ciudad + ",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0";
		JsonObject tiempo = arbol.leeJSON(url).asJsonObject();
		JsonArray weather = tiempo.getJsonArray("weather");
		JsonObject descripcion = null;
		
		for (int i = 0; i < weather.size(); i++) {
			descripcion = weather.getJsonObject(i);
		}
		
		return descripcion;
	}
	
	public static void main(String[] args) {
		JSON_ej1 ej1 = new JSON_ej1();
		System.out.println(ej1.devuelveTiempo("vigo").getString("description"));
	}
}