package RESTFul;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persona { // EJERCICIO 1
	private int id;
	private String cadena;
	private boolean casado;
	private String sexo;
	
	public Persona() {
		
	}
	
	public Persona(int id, String cadena, boolean casado, String sexo) {
		super();
		this.id = id;
		this.cadena = cadena;
		this.casado = casado;
		this.sexo = sexo;
	}
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	public boolean isCasado() {
		return casado;
	}
	public void setCasado(boolean casado) {
		this.casado = casado;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}