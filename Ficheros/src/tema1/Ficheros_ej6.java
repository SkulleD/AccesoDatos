package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ficheros_ej6 {
	public void uneFicheros(File[] files, String destino) {
		for (File f : files) {
			System.out.println(f.getAbsolutePath());
		}
		
		try {
			PrintWriter pw = new PrintWriter(files[0].getParent() + "/" + destino);
			pw.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	public void charasHorizontal(File fichero, int n, String rutaEscribir) throws IOException, FileNotFoundException {
		Ficheros_ej6 ej6 = new Ficheros_ej6();
		File file;
		try (FileReader filereader = new FileReader(fichero)) {
			char buffer[] = new char[n];
			int i;
			String aEscribir = "";
			int numArchivo = 0;
			
			while ((i = filereader.read(buffer)) != -1) {
				aEscribir = String.valueOf(buffer);
				file = new File(rutaEscribir + numArchivo);
				ej6.escribeFichero(file, aEscribir);
				numArchivo++;
				//System.out.println(new String(buffer, 0, i));
			}
		}
	}
	
	public void charasVertical() {
		
	}
	
	public void escribeFichero(File archivo, String cadena) throws IOException {
		System.out.println(archivo.getAbsolutePath());
		try (FileWriter filewriter = new FileWriter(archivo)) {
			for (int i = 0; i < cadena.length(); i++) {
				filewriter.write(cadena.charAt(i));
				filewriter.write(System.getProperty("line.separator"));
			}	
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String rutabase = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio6\\TEXTOBASE.txt";
		String rutaEscribir = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio6\\Archivo";
		File file = new File(rutabase);
		Ficheros_ej6 ej6 = new Ficheros_ej6();
		//File file = new File("");
		File[] files = new File[] {new File("ruta 1"), new File("ruta 2")};
		
		ej6.charasHorizontal(file, 5, rutaEscribir);
		//ej6.uneFicheros(files, "nuevo.txt");

	}
}