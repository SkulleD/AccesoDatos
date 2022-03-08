package RESTFul;

import java.lang.Thread.State;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	@GET
	@Path("buscar")
	public Response verPersona2(String nombre) {
		for (Persona perso : personas) {
			if (perso.getCadena().toLowerCase().equals(nombre.toLowerCase())) {
				return Response.ok(perso).build();
			}
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	// Apartado G
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response anadirPersona(ArrayList<Persona> personasList) {
		for (int i = 0; i < personasList.size(); i++) {
			personas.add(personasList.get(i));
		}

		return Response.ok("Funciona").build();
	}
	
	// Apartado H
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrarPersona(@PathParam("id") int id) {
		for (int i = 0; i <= personas.size(); i++) {
			if (personas.get(i).getId() == id) {
				personas.remove(i);
				return Response.ok("Persona borrada").build();
			}
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
}