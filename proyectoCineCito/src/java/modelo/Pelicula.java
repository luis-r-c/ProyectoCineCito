package modelo;

import java.io.InputStream;

public class Pelicula {
    int id;
    String nombres;    
    String imagen;
    InputStream foto;
    String descripcion;
    String calidad;
    String link;
  

    public Pelicula() {
    }    

    public Pelicula(int id, String nombres, String imagen, InputStream foto, String descripcion, String calidad, String link) {
        this.id = id;
        this.nombres = nombres;
        this.imagen = imagen;
        this.foto = foto;
        this.descripcion = descripcion;
        this.calidad = calidad;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
