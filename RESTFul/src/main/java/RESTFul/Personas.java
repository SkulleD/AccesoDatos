package RESTFul;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/personas")
public class Personas { // EJERCICIO 3
	static ArrayList<Persona> personas = new ArrayList<>();

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public String guardar(Persona persona) {
		personas.add(persona);
		return "Todo perfe";
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Persona> listar() {
		return this.personas;
	}

	@GET
	@Path("{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona ver(@PathParam("nombre") String nombre) {
		for (Persona perso : personas) {
			if (perso.getCadena() == nombre) {
				return perso;
			}
		}
		return null;
	}

	@GET
	@Path("buscar")
	@Produces(MediaType.APPLICATION_JSON) // DefaultValue es del ejercicio 9
	public Persona ver1(@DefaultValue("Álvaro") @QueryParam("nombre") String nombre) {
		System.out.println(nombre);
		for (Persona perso : personas) {
			if (perso.getCadena().toLowerCase().equals(nombre.toLowerCase())) {
				return perso;
			}
		}
		return null;
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String borrar(@PathParam("id") int id) {
		for (int i = 0; i <= personas.size(); i++) {
			if (personas.get(i).getId() == id) {
				personas.remove(i);
				return "La persona con el id: " + id + " ha sido borrada";
			}
		}
		return "Un error ocurrió";
	}
}
