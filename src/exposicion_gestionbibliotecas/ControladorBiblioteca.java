package exposicion_gestionbibliotecas;

import javax.swing.*;
import java.util.Date;
import java.io.Serializable;

public class ControladorBiblioteca implements Serializable {
    private static final long serialVersionUID = 1L;
    private Biblioteca biblioteca;

    public ControladorBiblioteca() {
        try {
            biblioteca = (Biblioteca) SerializacionUtil.cargarObjeto("data/biblioteca.dat");
        } catch (Exception e) {
            biblioteca = new Biblioteca();
        }
    }

    public void guardarDatos() {
        try {
            SerializacionUtil.guardarObjeto(biblioteca, "data/biblioteca.dat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean agregarLibro() {
        if (biblioteca.getAutores().length == 0) {
            JOptionPane.showMessageDialog(null, "No se puede agregar un libro hasta que haya autores registrados.");
            return false;
        }

        String titulo = JOptionPane.showInputDialog("Titulo del Libro:");
        if (titulo == null || titulo.isEmpty()) return false;

        String isbn = JOptionPane.showInputDialog("ISBN del Libro (solo numeros):");
        if (isbn == null || !isbn.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El ISBN debe contener solo numeros.");
            return false;
        }

        Autor autor = (Autor) JOptionPane.showInputDialog(null, "Selecciona el autor:", 
                "Autores", JOptionPane.QUESTION_MESSAGE, null, biblioteca.getAutores(), null);
        if (autor == null) return false;

        Libro libro = new Libro(titulo, autor, isbn);
        biblioteca.agregarLibro(libro);
        JOptionPane.showMessageDialog(null, "Libro agregado correctamente.");
        return true;
    }

    public boolean buscarLibro() {
        if (biblioteca.getLibros().length == 0) return false;
        String titulo = JOptionPane.showInputDialog("Introduce el titulo del libro a buscar:");
        if (titulo == null || titulo.isEmpty()) return false;

        StringBuilder resultados = new StringBuilder();
        for (Libro libro : biblioteca.buscarLibrosPorTitulo(titulo)) {
            resultados.append(libro.toString()).append("\n");
        }
        if (resultados.length() > 0) {
            JOptionPane.showMessageDialog(null, "Resultados:\n" + resultados.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron libros con ese titulo.");
        }
        return true;
    }

    public boolean agregarAutor() {
        String nombre = JOptionPane.showInputDialog("Nombre del Autor:");
        if (nombre == null || nombre.isEmpty()) return false;

        String nacionalidad = JOptionPane.showInputDialog("Nacionalidad del Autor:");
        if (nacionalidad == null || nacionalidad.isEmpty()) return false;

        Autor autor = new Autor(nombre, nacionalidad);
        biblioteca.agregarAutor(autor);
        JOptionPane.showMessageDialog(null, "Autor agregado correctamente.");
        return true;
    }

    public boolean registrarUsuario() {
        String nombre = JOptionPane.showInputDialog("Nombre del Usuario:");
        if (nombre == null || nombre.isEmpty()) return false;

        String correo = JOptionPane.showInputDialog("Correo del Usuario:");
        if (correo == null || correo.isEmpty()) return false;

        String telefono = JOptionPane.showInputDialog("Telefono del Usuario (solo numeros):");
        if (telefono == null || !telefono.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El telefono debe contener solo numeros.");
            return false;
        }

        Usuario usuario = new Usuario(nombre, correo, telefono);
        biblioteca.agregarUsuario(usuario);
        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
        return true;
    }

    public boolean buscarUsuario() {
        if (biblioteca.getUsuarios().length == 0) return false;

        String nombre = JOptionPane.showInputDialog("Introduce el nombre del usuario a buscar:");
        if (nombre == null || nombre.isEmpty()) return false;

        StringBuilder resultados = new StringBuilder();
        for (Usuario usuario : biblioteca.buscarUsuariosPorNombre(nombre)) {
            resultados.append(usuario.toString()).append("\n");
        }
        if (resultados.length() > 0) {
            JOptionPane.showMessageDialog(null, "Resultados:\n" + resultados.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron usuarios con ese nombre.");
        }
        return true;
    }

    public boolean registrarPrestamo() {
        if (biblioteca.getLibros().length == 0 || biblioteca.getUsuarios().length == 0) {
            JOptionPane.showMessageDialog(null, "No hay libros o usuarios registrados para realizar un prestamo.");
            return false;
        }

        Usuario usuario = (Usuario) JOptionPane.showInputDialog(null, "Selecciona el usuario:", 
                "Usuarios", JOptionPane.QUESTION_MESSAGE, null, biblioteca.getUsuarios(), null);
        if (usuario == null) return false;

        Libro libro = (Libro) JOptionPane.showInputDialog(null, "Selecciona el libro:", 
                "Libros", JOptionPane.QUESTION_MESSAGE, null, biblioteca.getLibros(), null);
        if (libro == null) return false;

        Date fechaPrestamo = new Date();
        Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (7 * 24 * 60 * 60 * 1000));

        Prestamo prestamo = new Prestamo(libro, usuario, fechaPrestamo, fechaDevolucion);
        biblioteca.registrarPrestamo(prestamo);
        JOptionPane.showMessageDialog(null, "Prestamo registrado correctamente.");
        return true;
    }

    public boolean devolverLibro() {
        if (biblioteca.getPrestamos().length == 0) {
            JOptionPane.showMessageDialog(null, "No hay prestamos registrados para devolver.");
            return false;
        }

        Prestamo prestamo = (Prestamo) JOptionPane.showInputDialog(null, "Selecciona el prestamo a devolver:", 
                "Prestamos", JOptionPane.QUESTION_MESSAGE, null, biblioteca.getPrestamos(), null);
        if (prestamo == null) return false;

        biblioteca.devolverLibro(prestamo);
        JOptionPane.showMessageDialog(null, "Libro devuelto correctamente.");
        return true;
    }

    public void verReportes() {
        StringBuilder reportes = new StringBuilder();
        reportes.append("LIBROS DISPONIBLES:\n");
        for (Libro libro : biblioteca.getLibros()) {
            if (libro.isDisponible()) {
                reportes.append(libro.toString()).append("\n");
            }
        }
        reportes.append("\nPRESTAMOS ACTUALES:\n");
        for (Prestamo prestamo : biblioteca.getPrestamos()) {
            reportes.append(prestamo.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, reportes.toString(), "Reportes", JOptionPane.INFORMATION_MESSAGE);
    }
}
