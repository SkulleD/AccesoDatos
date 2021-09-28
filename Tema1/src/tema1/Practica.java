package tema1;

import java.io.File;

public class Practica {
	String ruta = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Tema1\\src\\tema1\\prueba";
	File archivo = new File(ruta);

	public static void main(String[] args) {
		Practica practica = new Practica();
		practica.muestraCosas();
	}

	public void muestraCosas() {
		File[] lista = archivo.listFiles();

		if (archivo.exists()) {
			for (File file : lista) {
				if (file.isFile()) {
					System.out.println("-file-");
					System.out.println(file.getName());
				}
			}
			for (File file : lista) {
				if (file.isDirectory()) {
					System.out.println("-folder-");
					System.out.println(file.getName());
				}
			}
		}
	}
}