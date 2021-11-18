package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

public class JSON_CreaArbol {
	public JsonValue readHttp(String direccion) throws IOException { // LEE HTTP
		URL url = new URL(direccion);

		try (InputStream input = url.openStream(); JsonReader reader = Json.createReader(input)) {
			return reader.read();
		}
	}
	
	public JsonValue readHttps(String direccion) throws IOException { // LEE HTTPS
		URL url = new URL(direccion);
		HttpsURLConnection url_connection = (HttpsURLConnection) url.openConnection();
		
		try (InputStream input = url_connection.getInputStream(); JsonReader reader = Json.createReader(input)) {
			return reader.read();
		} finally {
			url_connection.disconnect();
		}
	}
	
	public JsonValue readFile(String direccion) throws FileNotFoundException { // LEE ARCHIVOS
		try (JsonReader reader = Json.createReader(new FileReader(direccion))) {
			return reader.read();
		}
	}

	public JsonValue readJSON(String ruta) { // MÉTODO PARA LEER T0D0
		try {
			if (ruta.toLowerCase().startsWith("http://")) {
				return readHttp(ruta);
			} else if (ruta.toLowerCase().startsWith("https://")) {
				return readHttps(ruta);
			} else {
				readFile(ruta);
			}
		} catch (IOException e) {
			System.out.println("Error processing Json document" + e.getLocalizedMessage());
			return null;
		}
		return null;
	}

	public static void main(String[] args) {
		
	}
}