package jsonpackage;

public class JSON_ej5 {
	static JSONCreaArbol arbol = new JSONCreaArbol();

	public static String devuelveNombre(int id) {
		String url = "http://api.openweathermap.org/data/2.5/weather?id=" + id + "&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0";
		return arbol.leeJSON(url).asJsonObject().getString("name");
	}
	
	public static void main(String[] args) {
		int id = 3105976;
		System.out.printf("Nombre: %s", devuelveNombre(id));
	}
}
