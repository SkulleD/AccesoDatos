package jsonpackage;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class JSON_ej2 {
	static JSONCreaArbol arbol = new JSONCreaArbol();
	JsonObject tiempo;
	double latitud = 35.82380908513249, longitud = 82.021484375;
	String url ="http://api.openweathermap.org/data/2.5/weather?lat=" + latitud + "&lon=" + longitud + "&APPID=a975f935caf274ab016f4308ffa23453";
	
	private JsonObject datosTiempo(double latitud, double longitud) {
		tiempo = arbol.leeJSON(url).asJsonObject();
		JsonArray weather = tiempo.getJsonArray("weather");
		JsonObject description = null;
		
		for (int i = 0; i < weather.size(); i++) {
			description = weather.getJsonObject(i);
		}
		
		return description;
	}
	
	public static void main(String[] args) {
		JSON_ej2 ej2 = new JSON_ej2();
		System.out.printf("%s\n%s", arbol.leeJSON(ej2.url).asJsonObject().getString("name"), ej2.datosTiempo(ej2.latitud, ej2.longitud).getString("description"));
	}
}