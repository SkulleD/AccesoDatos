package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ficheros_ej9 {
	Alumno alumno = new Alumno();
	static Ficheros_ej9 ej9 = new Ficheros_ej9();
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
	
	public void muestraAlumnos() {
		for (Alumno alumno : listado) {
			System.out.printf("\nCódigo: %d\nNombre: %s\nAltura: %.2f", alumno.getCodigo(), alumno.getNombre(), alumno.getAltura());
		}
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
		
		ej9.nuevoAlumno(21, "Alumno1", 1.82);
		ej9.nuevoAlumno(32, "Alumno2", 1.56);
		ej9.nuevoAlumno(43, "Alumno3", 1.75);
		ej9.nuevoAlumno(54, "Alumno4", 1.89);
		ej9.muestraAlumnos();
	}
}