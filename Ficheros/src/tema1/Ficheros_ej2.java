package tema1;

import java.io.File;

public class Ficheros_ej2 {
	String ruta = "C:\\Users\\AlvaroVila\\Desktop\\CLASE";
	File archivo = new File(ruta);
	
	public static void main(String[] args) {
		Ficheros_ej2 ficheros = new Ficheros_ej2();
		//ficheros.mostrar();
	}
	
	public void mostrar(File direccion) {
		File[] lista = archivo.listFiles();
		
		if(archivo.exists()) {
			for (File file : lista) {
				System.out.println(file.getName());
				
				//for (File direccion : lista) {
				//	System.out.println(file.getName());
				//}
			}
		}
	}
}
