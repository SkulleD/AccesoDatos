package RESTFul;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/persona")
public class GestionaPersona {
	static Persona persona;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Persona datosPersona() {
		return new Persona(42, "Álvaro", false, "Hombre");
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces (MediaType.TEXT_PLAIN)
	public String guardar(Persona persona) {
		this.persona = persona;
		return "Ta bien";
	}
}