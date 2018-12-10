
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
import java.beans.Statement;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;

@Path("/libros")
public class SrvLibro {
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaLibros(){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
    EntityManager em;
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
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
            EntityManager em = emf.createEntityManager();
            
            EntityTransaction tx = em.getTransaction();
            tx.begin();
                
            Query query = em.createNativeQuery("INSERT INTO libros (isbn, nombre, editorial, autores, paginas, imagen, url, descripcio) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)");
            query.setParameter(1, libro.getIsbn());
            query.setParameter(2, libro.getNombre());
            query.setParameter(3, libro.getEditorial());
            query.setParameter(4, libro.getAutores());
            query.setParameter(5, libro.getPaginas());
            query.setParameter(6, libro.getImagen());
            query.setParameter(7, libro.getUrl());
            query.setParameter(8, libro.getDescripcion());
            int resultado = query.executeUpdate();
            tx.commit();
            em.close();
            if(resultado == 1){
                return Response.status(Response.Status.CREATED).build();    
            } else {
                return Response.serverError().entity("Ocurrio un error al Guardar - " + Integer.toString(resultado)).build();
            }
            
            /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
            EntityManager em;
            
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();*/

            //return Response.status(Response.Status.CREATED).build();
            
            /*UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");//java:comp/UserTransaction
            transaction.begin();   
            em = emf.createEntityManager();
            em.persist(libro);
            transaction.commit();
            return Response.status(Response.Status.CREATED).build();*/
            
            /*em = emf.createEntityManager();            
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(libro);
            tx.commit();
            return Response.status(Response.Status.CREATED).build();*/
            
            /*em.getTransaction().begin();
            Query query = em.createNativeQuery("INSERT INTO libros (isbn, nombre, editorial, autores, paginas, imagen, url, descripcio) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)");
            query.setParameter(1, libro.getIsbn());
            query.setParameter(2, libro.getNombre());
            query.setParameter(3, libro.getEditorial());
            query.setParameter(4, libro.getAutores());
            query.setParameter(5, libro.getPaginas());
            query.setParameter(6, libro.getImagen());
            query.setParameter(7, libro.getUrl());
            query.setParameter(8, libro.getDescripcion());
            int resultado = query.executeUpdate();
            em.getTransaction().commit();
            em.close();
            if(resultado == 1){
                return Response.status(Response.Status.CREATED).build();    
            } else {
                return Response.serverError().entity("Ocurrio un error al Guardar - " + Integer.toString(resultado)).build();
            }*/
        } catch (Exception e) {
            return Response.serverError().entity("Error al guardar el libro - " + e.getMessage()).build();
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaLibro(DBLibro libro){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
            EntityManager em;
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
