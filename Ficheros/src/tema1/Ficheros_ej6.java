package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Ficheros_ej6 {

	public static void main(String[] args) throws FileNotFoundException {
		Ficheros_ej6 ej6 = new Ficheros_ej6();
		//File file = new File("");
		File[] files = new File[] {new File("ruta 1"), new File("ruta 2")};
		
		ej6.uneFicheros(files, "nuevo.txt");
	}
	
	public void uneFicheros(File [] files, String destino) {
		for (File f : files) {
			System.out.println(f.getAbsolutePath());
		}
		
		try {
			PrintWriter pw = new PrintWriter(files[0].getParent() + "/" + destino);
			pw.close();
		} catch (FileNotFoundException e) {
			
		}
	}
}
