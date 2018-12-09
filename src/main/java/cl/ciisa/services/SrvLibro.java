
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
import javax.ws.rs.PUT;
import javax.ws.rs.POST;

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
            if(lista.size() <= 0){
                return Response.noContent().build();
            } else {
                return Response.ok().entity(lista).build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Error al listar los libros - " + e.getMessage()).build();
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarLibro(DBLibro libro){
        try {            
            em = emf.createEntityManager();
            //em.getTransaction().begin();
            em.persist(libro);
            //em.getTransaction().commit();
            
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error al guardar el libro - " + e.getMessage()).build();
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaLibro(DBLibro libro){
        try {
            em = emf.createEntityManager();
            em.persist(libro);
            em.getTransaction().begin();
            libro = em.merge(libro);
            em.getTransaction().commit();            
            
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error al actualizar el libro - " + e.getMessage()).build();
        }
    }
}
