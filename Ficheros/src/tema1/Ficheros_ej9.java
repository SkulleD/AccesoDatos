package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ficheros_ej9 {
	Alumno alumno = new Alumno();
	static Ficheros_ej9 ej9 = new Ficheros_ej9();
	File fileAlumnos = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio9\\alumnos.txt");
	File fileAux = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio9\\auxiliar.txt");
	String cadena = "";

	public String nuevoAlumno(int codigo, String nombre, double altura, ArrayList<Alumno> listado) {
		if (siExiste(codigo, listado)) {
			cadena = "(!) Ya existe un alumno con ese código.";
			return cadena;
		} else {
			alumno.setCodigo(codigo);
			alumno.setNombre(nombre);
			alumno.setAltura(altura);
			ej9.anadirEnArchivo(fileAlumnos, listado);
		}
		return cadena;
	}

	public void consultarAlumnos(ArrayList<Alumno> listado) {
		for (Alumno alumno : listado) {
			System.out.printf("\nCódigo: %d\nNombre: %s\nAltura: %.2f\n", alumno.getCodigo(), alumno.getNombre(),
					alumno.getAltura());
		}
	}

	public String modificarAlumnos(int codigo, ArrayList<Alumno> listado) {
		if (siExiste(codigo, listado)) {

		} else {
			cadena = "(!) No existe ningún alumno con ese código.";
			return cadena;
		}
		return cadena;
	}

	public String borrarAlumnos(int codigo, ArrayList<Alumno> listado) {
		if (siExiste(codigo, listado)) {
			listado.remove(alumno);

			cadena = "Se ha borrado el alumno";
		} else {
			cadena = "(!) No existe ningún alumno con ese código.";
			return cadena;
		}
		return cadena;
	}

	public boolean siExiste(int codigo, ArrayList<Alumno> listado) {
		boolean existe = false;

		for (Alumno alumno : listado) {
			if (alumno.getCodigo() == codigo) {
				existe = true;
			}
		}
		return existe;
	}

	public void anadirEnArchivo(File file, ArrayList<Alumno> listado) {
		try (FileOutputStream output = new FileOutputStream(file)) {
			for (Alumno alumno : listado) {
				output.write(alumno.getCodigo());
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	public static void main(String[] args) {
		ArrayList<Alumno> listado = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			listado.add(new Alumno(i, "Alumno " + i, i + 100));
		}
		ej9.consultarAlumnos(listado);
	}
}