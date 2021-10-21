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
	String cadena = "";
	static Alumno alumno = new Alumno();
	boolean existe = false;

	public void nuevoAlumno(File file, Alumno alumno) throws IOException {
		if (siExiste(alumno.getCodigo())) {
			try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file, true))) {
				output.writeInt(alumno.getCodigo());
				output.writeUTF(alumno.getNombre());
				output.writeFloat(alumno.getAltura());
			} catch (EOFException e) {
				System.out.println("Fin de fichero");
			} catch (IOException e) {

			}
		} else {
			System.out.println("Ya existe un alumno con ese código");
		}
	}

	public void consultarAlumnos(File file) {
		try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
			int codigo;
			String nombre;
			float altura;
			
			while (true) {
				codigo = input.readInt();
				nombre = input.readUTF();
				altura = input.readFloat();
				System.out.printf("Código: %d\nNombre: %s\nAltura: %.2f", codigo, nombre, altura);
				System.out.println("\n");
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	public void modificarAlumnos() {

	}

	public void borrarAlumnos() {

	}
	
	public boolean siExiste(int codigo) {
		if (alumno.getCodigo() == codigo) {
			existe = true;
		} else {
			existe = false;
		}
		
		return existe;
	}

	public static void main(String[] args) throws IOException {
		File fileAlumnos = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio9\\alumnos.dat");
		File fileAux = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio9\\auxiliar.dat");
		Ficheros_ej9 ej9 = new Ficheros_ej9();

		for (int i = 1; i <= 5; i++) {
			alumno.setCodigo(i);
			alumno.setNombre("Nombre" + i);
			alumno.setAltura(i + 100);
			ej9.nuevoAlumno(fileAlumnos, alumno);
			
		}
		ej9.consultarAlumnos(fileAlumnos);
		
	}
}