public class Juego {
	private int valoracion;
	private double precio;
	private String nombre;

	public Juego(int valoracion, double precio, String nombre) {
		super();
		this.valoracion = valoracion;
		this.precio = precio;
		this.nombre = nombre;
	}
	
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return valoracion + "\t" + precio + "\t" + nombre;
	}
}