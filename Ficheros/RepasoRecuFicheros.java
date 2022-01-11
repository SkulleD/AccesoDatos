import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RepasoRecuFicheros {
	public static Juego readTab(File fichero, int posicion) throws FileNotFoundException {
		int cont = 1;

		if (fichero.exists() && fichero.isFile()) {
			try (Scanner sc = new Scanner(fichero)) {
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

	public static void main(String[] args) {
		File file = new File(
				"C:\\Users\\Álvaro Vila\\Desktop\\CLASE\\DAM 2 DEFINITIVE EDITION\\AD - Acceso a Datos\\Práctica\\Repaso AD 1a EVA\\juegos.txt");

		try {
			Juego juego = readTab(file, 2);
			
			if (juego != null) {
				System.out.println(juego);
			} else {
				System.out.println("Ese juego no existe");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
