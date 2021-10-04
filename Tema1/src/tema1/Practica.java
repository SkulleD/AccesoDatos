package tema1;

import java.io.File;

public class Practica {
	public static void main(String[] args) {
		Practica practica = new Practica();
		String ruta = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Tema1\\src\\tema1\\prueba";
		File archivo = new File(ruta);
		
		practica.muestraCosas(archivo);
	}

	public void muestraCosas(File archivo) {
		if (archivo.exists()) {
			File[] lista = archivo.listFiles();
			for (File file : lista) {
				if (file.isFile()) {
					System.out.print("-file:-");
					System.out.println(file.getName());
				}
			}
			for (File file : lista) {
				if (file.isDirectory()) {
					System.out.print("-folder:-");
					System.out.println(file.getName());
				}
			}
		}
	}
}