package jsonpackage;

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class JSON_ej3 {
	static JSONCreaArbol arbol = new JSONCreaArbol();
	static ArrayList<String> arrayList = new ArrayList<>();
	
	public static ArrayList<String> devuelvePredicciones(double lon, double lat, int cantidad) {
		String url = "http://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt=" + cantidad +"&APPID=8f8dccaf02657071004202f05c1fdce0";
		JsonObject raiz = arbol.leeJSON(url).asJsonObject();
		JsonArray lista = raiz.getJsonArray("list");
		JsonObject ciudad = null;
		JsonArray weather = null;
		JsonObject description = null;
		
		for (int i = 0; i < lista.size(); i++) {
			ciudad = lista.getJsonObject(i);
			
			weather = ciudad.getJsonArray("weather");
			
			for (int j = 0; j < weather.size(); j++) {
				description = weather.getJsonObject(j);
				arrayList.add(description.getString("description"));
			}
		}
		return arrayList;
	}
	
	public static void main(String[] args) {
		int cantidad = 7;
		double lat = 42.2328;
		double lon = -8.7226;
		
		for (String elemento : devuelvePredicciones(lon, lat, cantidad)) {
			System.out.println(elemento);
		}
	}
}