package tema1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ficheros_ej9 {
	Alumno alumno;
	static Ficheros_ej9 ej9 = new Ficheros_ej9();
    File fileAlumnos = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio9\\alumnos.txt");
    File fileAux = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio9\\auxiliar.txt");
	String cadena = "";

	public String nuevoAlumno(int codigo, String nombre, float altura, ArrayList<Alumno> listado) throws FileNotFoundException, IOException {

			//cadena = "(!) Ya existe un alumno con ese código.";
	

			alumno.setCodigo(codigo);
			alumno.setNombre(nombre);
			alumno.setAltura(altura);
			anadirEnArchivo(alumno.getCodigo(), fileAlumnos, listado);

		return cadena;
	}

	public void consultarAlumnos(File file, ArrayList<Alumno> listado) {
		try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
			int codigo;
			String nombre;
			float altura;

			while (true) {
				codigo = input.readInt();
				nombre = input.readUTF();
				altura = input.readFloat();
				System.out.printf("Código: %d\nNombre: %s\nAltura: %f", codigo, nombre, altura);
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

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
			//listado.remove(alumno);

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

	public void anadirEnArchivo(int codigo, File file, ArrayList<Alumno> listado) throws FileNotFoundException, IOException {
		//if (!siExiste(codigo, listado)) {
			try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file))) {
				for (Alumno alumno : listado) {
					output.writeInt(alumno.getCodigo());
					output.writeUTF(alumno.getNombre());
					output.writeFloat(alumno.getAltura());
				}
			} catch (EOFException e) {
				System.out.println("Fin de fichero");
		//	}
		}
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Alumno> listado = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			listado.add(new Alumno(i, "Alumno " + i, i + 100));
			ej9.nuevoAlumno(i, "Alumno " + i, i, listado);
		}
		//ej9.nuevoAlumno(2, "Alumno " + 2, 175, listado);
		ej9.consultarAlumnos(ej9.fileAlumnos, listado);
	}
}