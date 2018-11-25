
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
import cl.ciisa.data.DBLibro;

@Path("/libros")
public class SrvLibro {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
    EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaLibros(){
        try {
            em = emf.createEntityManager();
            List<DBLibro> lista = em.createNamedQuery("DBLibro.findAll").getResultList();
            return Response.ok().entity(lista).build();
        } catch (Exception e) {
            return Response.ok(500).entity("Error al listar los datos - " + e.getMessage()).build();
        }
    }
}
