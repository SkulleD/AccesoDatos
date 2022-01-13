import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class RepasoRecuFicheros {
	public static Juego readTab(File fichero, int posicion) throws FileNotFoundException {
		int cont = 1;

		if (fichero.exists() && fichero.isFile()) {
			try (Scanner sc = new Scanner(fichero)) {
				// sc.useLocale(Locale.US); // Se usa idioma inglés si los doubles se separan
				// por puntos en vez de comas
				if (sc.hasNext()) {
					sc.nextLine();
				}

				while (sc.hasNext()) {
					if (cont == posicion) {
						return new Juego(sc.nextInt(), sc.nextDouble(), sc.nextLine());
					} else {
						sc.nextLine();
					}

					cont++;
				}
			}
		}

		return null;
	}

	public static boolean addJuego(File ficheroDat, Juego juego) throws FileNotFoundException, IOException {
		if (juego != null) {
			try (DataOutputStream output = new DataOutputStream(new FileOutputStream(ficheroDat, true))) {
				output.writeInt(juego.getValoracion());
				output.writeDouble(juego.getPrecio());
				output.writeUTF(juego.getNombre());
				return true;
			}
		}

		return false;
	}

	public static void showAll(File ficheroDat) throws FileNotFoundException, IOException, EOFException {
		String cadena;
		
		try (DataInputStream input = new DataInputStream(new FileInputStream(ficheroDat))) {
			while (input.available() > 0) {
				System.out.printf("%d\t%f\t%s\n", input.readInt(), input.readDouble(), input.readUTF());
			}
		}
	}

	public static void addAll(File fichero, File ficheroDat) throws IOException {
		boolean running = true;
		int cont = 1;
		Juego juego;
		
		while (running) {
			juego = readTab(fichero, cont);
			running = addJuego(fichero, juego);
			cont++;
		}
	}
	
	public static void main(String[] args) {
		File file = new File(
				"C:\\Users\\Álvaro Vila\\Desktop\\CLASE\\DAM 2 DEFINITIVE EDITION\\AD - Acceso a Datos\\Práctica\\Repaso AD 1a EVA\\juegos.txt");

		File fileDat = new File(
				"C:\\Users\\Álvaro Vila\\Desktop\\CLASE\\DAM 2 DEFINITIVE EDITION\\AD - Acceso a Datos\\Práctica\\Repaso AD 1a EVA\\juegos.dat");
		try {
			Juego juego = readTab(file, 9);
			System.out.println("Escribir");
			System.out.println(addJuego(fileDat, juego));
			System.out.println("Leer");

			addAll(file, fileDat);
			
			showAll(fileDat);

			if (juego != null) {
				System.out.println(juego);
			} else {
				System.out.println("Ese juego no existe");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
