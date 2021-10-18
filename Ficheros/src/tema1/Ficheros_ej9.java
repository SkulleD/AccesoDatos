package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ficheros_ej9 {
	Alumno alumno = new Alumno();
	Ficheros_ej9 ej9 = new Ficheros_ej9();
	ArrayList<Alumno> listado = new ArrayList<>();
	
	public void nuevoAlumno(int codigo, String nombre, double altura) {
		alumno.setCodigo(codigo);
		alumno.setNombre(nombre);
		alumno.setAltura(altura);
		ej9.anadirEnlistado(alumno);
	}
	
	public void consultarAlumnos() {
		
	}
	
	public void modificarAlumnos() {
		
	}
	
	public void borrarAlumnos() {
		
	}
	
	public void anadirEnArchivo(File file) {
		try (FileOutputStream output = new FileOutputStream(file)) {
			int c = 0;
			output.write(c);
			
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}
	
	public void anadirEnlistado(Alumno alumno) {
		listado.add(alumno);
	}
	
	public static void main(String[] args) {
		File fileAlumnos = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio9\\alumnos.dat");
		
		ej9.nuevoAlumno(42, "Álvaro", 1.82);
	}
}