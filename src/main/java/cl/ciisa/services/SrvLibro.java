
package cl.ciisa.services;

import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/libros")
public class SrvLibro {
    
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("UP_Datos");
    //EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaLibros(){
        try {
            return Response.ok().entity("Hola mundo").build();
        } catch (Exception e) {
            return Response.ok(500).entity("Error al listar los datos - " + e.getMessage()).build();
        }
    }
}
