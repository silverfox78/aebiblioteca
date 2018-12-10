package cl.ciisa.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cl.ciisa.data.DBLibro;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import org.apache.commons.lang3.math.NumberUtils;

@Path("/libros")
public class SrvLibro {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaLibros() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
        EntityManager em = emf.createEntityManager();
        Response retorno = null;

        try {
            List<DBLibro> lista = em.createNamedQuery("DBLibro.findAll").getResultList();
            if (lista.size() <= 0) {
                retorno = Response.noContent().build();
            } else {
                retorno = Response.ok().entity(lista).build();
            }
        } catch (Exception e) {
            retorno = Response.serverError().entity("Error al listar los libros - " + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarLibroPorId(@PathParam("id") String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
        EntityManager em = emf.createEntityManager();
        Response retorno = null;

        try {
            this.esNumero(id);
            DBLibro libro = em.find(DBLibro.class, Integer.parseInt(id));
            retorno = Response.ok(libro).build();
        } catch (Exception e) {
            retorno = Response.serverError().entity("Error, no se encontro el libro - " + e.getMessage()).build();
        } finally {
            em.close();
        }

        return retorno;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarLibro(DBLibro libro) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            this.validaLibro(libro, true);
            tx.begin();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            libro.setCrtdFecha(date);
            em.persist(libro);
            tx.commit();
            
            retorno = Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity("Error al guardar el libro - " + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizaLibro(DBLibro libro) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            this.validaLibro(libro, false);
            tx.begin();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            libro.setLupdFecha(date);
            libro = em.merge(libro);
            tx.commit();
            
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity("Error al actualizar el libro - " + e.getMessage()).build();
        } finally {
            em.close();
        }
        return retorno;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminaLibro(@PathParam("id") String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBLibro");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Response retorno = null;

        try {
            ///this.esNumero(id);
            tx.begin();
            DBLibro libro = em.getReference(DBLibro.class, Integer.parseInt(id));
            em.remove(libro);
            tx.commit();
            retorno = Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            tx.rollback();
            retorno = Response.serverError().entity("Error al eliminar el libro - " + e.getMessage()).build();
        } finally {
            em.close();
        }

        return retorno;
    }
    
    private void esNumero(String valor){
        /*if(valor != null && !valor.isEmpty()){
            throw new IllegalArgumentException("El argumento informado no es valido [1]");
        }
        if(!NumberUtils.isNumber(valor)){
            throw new IllegalArgumentException("El argumento informado no es valido [2]");
        }
        int numero = Integer.parseInt(valor);
        if(numero <= 0){
            throw new IllegalArgumentException("El argumento informado no es valido [3]");
        }*/
    }
    
    private void validaTexto(String texto, int largo, String nombre){
        /*if(texto != null && !texto.isEmpty()){
            throw new IllegalArgumentException("El campo [" + nombre + "] informado no es obligatorio.");
        }
        int tamanno = texto.length();
        if(tamanno <= 0 || tamanno > largo){
            throw new IllegalArgumentException("El campo [" + nombre + "] debe tener entre 0 y [" + Integer.toString(largo) + "] caracteres.");
        }*/
    }
    
    private void validaLibro(DBLibro libro, boolean inserta){
        /*if(libro == null){
            throw new IllegalArgumentException("La informacion del libro no es valida.");
        }
        
        if(!inserta){
            if(libro.getId() <= 0){
                throw new IllegalArgumentException("El ID informado no es valido.");
            }
        }
        
        this.validaTexto(libro.getIsbn(), 100, "ISBN");
        this.validaTexto(libro.getNombre(), 300, "NOMBRE");
        this.validaTexto(libro.getEditorial(), 300, "EDITORIAL");
        this.validaTexto(libro.getAutores(), 300, "AUTORES");
        
        if(libro.getPaginas() <= 0){
            throw new IllegalArgumentException("La cantidad de paginas informada no es valida");
        }*/
    }
}
