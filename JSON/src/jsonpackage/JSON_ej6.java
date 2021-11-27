package jsonpackage;

import javax.json.JsonObject;

public class JSON_ej6 {
	static JSONCreaArbol arbol = new JSONCreaArbol();
	
	public static double[] devuelveCords(String ciudad) {
		String url ="http://api.openweathermap.org/data/2.5/weather?q=" + ciudad + ",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0";
		JsonObject raiz = arbol.leeJSON(url).asJsonObject();
		JsonObject coord = raiz.getJsonObject("coord");
		
		return new double[] {coord.getJsonNumber("lon").doubleValue(), coord.getJsonNumber("lat").doubleValue()};
	}
	
	public static void main(String[] args) {
		String ciudad = "ourense";
		double res[] = devuelveCords(ciudad);
 		System.out.printf("Longitud: %f\nLatitud: %f", res[0], res[1]);
	}
}