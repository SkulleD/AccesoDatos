package tema1;

public class Alumno {
		private int codigo;
		private String nombre;
		private double altura;
		
		public Alumno() {
			this.codigo = 0;
			this.nombre = "";
			this.altura = 0;
		}
		
		public Alumno(int codigo, String nombre, double altura) {
			this.codigo = codigo;
			this.nombre = nombre;
			this.altura = altura;
		}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public double getAltura() {
			return altura;
		}

		public void setAltura(double altura) {
			this.altura = altura;
		}
}
