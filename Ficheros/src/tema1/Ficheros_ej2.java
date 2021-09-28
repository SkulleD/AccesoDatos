package tema1;

import java.io.File;

public class Ficheros_ej2 {
	public static void main(String[] args) {
		Ficheros_ej2 ficheros = new Ficheros_ej2();
		String ruta = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Tema1\\src";
		File archivo = new File(ruta);
		
		ficheros.ejercicio2(archivo);
	}
	
	public void ejercicio2(File archivo) {
		File[] lista = archivo.listFiles();
		
		if(archivo.exists()) {
			for (File file : lista) {
				file.getAbsolutePath();
				System.out.println(file.getAbsoluteFile());
			}
		}
	}
}