package RESTFul;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/persona")
public class RepasoREST {
	static Persona persona;
	static ArrayList<Persona> personas = new ArrayList<>();

	// EJERCICIO 2

	// Apartado A
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Persona datosPersona() {
		return new Persona(24, "Alvaro", false, "hombre");
	}

	// Apartado B
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.TEXT_PLAIN)
	public String guardar(Persona persona) {
		this.persona = persona;
		return "Todo correcto";
	}

	// EJERCICIO 3

	// Apartado A
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.TEXT_PLAIN)
	public String guardarPersona(Persona persona) {
		personas.add(persona);
		return "Persona añadida";
	}
	
	// Apartado B
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Persona> listaPersonas() {
		return personas;
	}
	
	// Apartado C
	@GET
	@Path("{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona verPersona(@PathParam("nombre") String nombre) {
		for (Persona perso : personas) {
			if (persona.getCadena().equals(nombre)) {
				return perso;
			}
		}
		
		return null;
	}
	
	// Apartado D
	
}