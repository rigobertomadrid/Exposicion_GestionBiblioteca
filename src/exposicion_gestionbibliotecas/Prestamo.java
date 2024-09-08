package exposicion_gestionbibliotecas;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Libro libro;
    private Usuario usuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(Libro libro, Usuario usuario, Date fechaPrestamo, Date fechaDevolucion) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        libro.setDisponible(false); 
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Prestamo del libro: " + libro.getTitulo() + " a " + usuario.getNombre() + 
               " (Fecha de prestamo: " + sdf.format(fechaPrestamo) +
               ", Fecha de devolucion: " + sdf.format(fechaDevolucion) + ")";
    }
}
