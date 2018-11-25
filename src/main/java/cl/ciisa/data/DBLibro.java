/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel Barrera
 */
@Entity
@Table(name = "libros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DBLibro.findAll", query = "SELECT d FROM DBLibro d")
    , @NamedQuery(name = "DBLibro.findById", query = "SELECT d FROM DBLibro d WHERE d.id = :id")
    , @NamedQuery(name = "DBLibro.findByIsbn", query = "SELECT d FROM DBLibro d WHERE d.isbn = :isbn")
    , @NamedQuery(name = "DBLibro.findByNombre", query = "SELECT d FROM DBLibro d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "DBLibro.findByEditorial", query = "SELECT d FROM DBLibro d WHERE d.editorial = :editorial")
    , @NamedQuery(name = "DBLibro.findByAutores", query = "SELECT d FROM DBLibro d WHERE d.autores = :autores")
    , @NamedQuery(name = "DBLibro.findByPaginas", query = "SELECT d FROM DBLibro d WHERE d.paginas = :paginas")
    , @NamedQuery(name = "DBLibro.findByImagen", query = "SELECT d FROM DBLibro d WHERE d.imagen = :imagen")
    , @NamedQuery(name = "DBLibro.findByUrl", query = "SELECT d FROM DBLibro d WHERE d.url = :url")
    , @NamedQuery(name = "DBLibro.findByDescripcion", query = "SELECT d FROM DBLibro d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DBLibro.findByCrtdFecha", query = "SELECT d FROM DBLibro d WHERE d.crtdFecha = :crtdFecha")
    , @NamedQuery(name = "DBLibro.findByLupdFecha", query = "SELECT d FROM DBLibro d WHERE d.lupdFecha = :lupdFecha")})
public class DBLibro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "isbn")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "editorial")
    private String editorial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "autores")
    private String autores;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paginas")
    private int paginas;
    @Size(max = 1000)
    @Column(name = "imagen")
    private String imagen;
    @Size(max = 1000)
    @Column(name = "url")
    private String url;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "crtd_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtdFecha;
    @Column(name = "lupd_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lupdFecha;

    public DBLibro() {
    }

    public DBLibro(Integer id) {
        this.id = id;
    }

    public DBLibro(Integer id, String isbn, String nombre, String editorial, String autores, int paginas) {
        this.id = id;
        this.isbn = isbn;
        this.nombre = nombre;
        this.editorial = editorial;
        this.autores = autores;
        this.paginas = paginas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getCrtdFecha() {
        return crtdFecha;
    }

    public void setCrtdFecha(Date crtdFecha) {
        this.crtdFecha = crtdFecha;
    }

    public Date getLupdFecha() {
        return lupdFecha;
    }

    public void setLupdFecha(Date lupdFecha) {
        this.lupdFecha = lupdFecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBLibro)) {
            return false;
        }
        DBLibro other = (DBLibro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.ciisa.data.DBLibro[ id=" + id + " ]";
    }
    
}
