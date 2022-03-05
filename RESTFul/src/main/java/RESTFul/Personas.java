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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/personas")
public class Personas { // EJERCICIO 3
	static ArrayList<Persona> personas = new ArrayList<>();

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) // 1
	@Produces(MediaType.TEXT_PLAIN)
	public Response guardar(Persona persona) {
		personas.add(persona);
		return Response.ok("Todo perfe (RESPONSE)").build(); // 12
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) // 2
	public Response listar() {
		return Response.ok(this.personas).build(); // 12
	}

	@GET
	@Path("{nombre}")
	@Produces(MediaType.APPLICATION_JSON) // 3
	public Response ver(@PathParam("nombre") String nombre) {
		for (Persona perso : personas) {
			if (perso.getCadena().equals(nombre)) {
				return Response.ok(perso).build();
			}
		}
		System.out.println("peté");
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("buscar")
	@Produces(MediaType.APPLICATION_JSON) // 4. DefaultValue es del apartado 9
	public Response ver1(@DefaultValue("Álvaro") @QueryParam("nombre") String nombre) {
		System.out.println(nombre);
		for (Persona perso : personas) {
			if (perso.getCadena().toLowerCase().equals(nombre.toLowerCase())) {
				return Response.ok(perso).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) // 7
	// @Produces(MediaType.APPLICATION_JSON)
	public Response anadir(ArrayList<Persona> personasList) {
		for (int i = 0; i < personasList.size(); i++) {
			personas.add(personasList.get(i));
		}

		return Response.ok("Funcionooooo (RESPONSE)").build(); // 12
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN) // 8
	public Response borrar(@PathParam("id") int id) {
		for (int i = 0; i <= personas.size(); i++) {
			if (personas.get(i).getId() == id) {
				personas.remove(i);
				return Response.ok("Funciona (RESPONSE)").build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("XML")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) // 10
	public Response devolver() {
		return Response.ok(this.personas).build();
	}

	@GET
	@Path("galego")
	@Produces(MediaType.TEXT_PLAIN) // 11
	public ArrayList<Persona> enGalego() {

		return this.personas;
	}
}
