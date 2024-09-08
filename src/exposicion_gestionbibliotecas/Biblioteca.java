package exposicion_gestionbibliotecas;

import java.io.Serializable;

public class Biblioteca implements Serializable {

    private static final long serialVersionUID = 1L;
    private Libro[] libros;
    private Autor[] autores;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private int numLibros;
    private int numAutores;
    private int numUsuarios;
    private int numPrestamos;

    public Biblioteca() {
        libros = new Libro[10];
        autores = new Autor[10];
        usuarios = new Usuario[10];
        prestamos = new Prestamo[10];
        numLibros = 0;
        numAutores = 0;
        numUsuarios = 0;
        numPrestamos = 0;
    }

    public void agregarAutor(Autor autor) {
        if (numAutores == autores.length) {
            expandirAutores();
        }
        autores[numAutores++] = autor;
    }

    private void expandirAutores() {
        Autor[] nuevoArray = new Autor[autores.length * 2];
        System.arraycopy(autores, 0, nuevoArray, 0, autores.length);
        autores = nuevoArray;
    }

    public void agregarLibro(Libro libro) {
        if (numLibros == libros.length) {
            expandirLibros();
        }
        libros[numLibros++] = libro;
    }

    private void expandirLibros() {
        Libro[] nuevoArray = new Libro[libros.length * 2];
        System.arraycopy(libros, 0, nuevoArray, 0, libros.length);
        libros = nuevoArray;
    }

    public void agregarUsuario(Usuario usuario) {
        if (numUsuarios == usuarios.length) {
            expandirUsuarios();
        }
        usuarios[numUsuarios++] = usuario;
    }

    private void expandirUsuarios() {
        Usuario[] nuevoArray = new Usuario[usuarios.length * 2];
        System.arraycopy(usuarios, 0, nuevoArray, 0, usuarios.length);
        usuarios = nuevoArray;
    }

    public void registrarPrestamo(Prestamo prestamo) {
        if (numPrestamos == prestamos.length) {
            expandirPrestamos();
        }
        prestamos[numPrestamos++] = prestamo;
    }

    private void expandirPrestamos() {
        Prestamo[] nuevoArray = new Prestamo[prestamos.length * 2];
        System.arraycopy(prestamos, 0, nuevoArray, 0, prestamos.length);
        prestamos = nuevoArray;
    }

    public void devolverLibro(Prestamo prestamo) {
        prestamo.getLibro().setDisponible(true);
        for (int i = 0; i < numPrestamos; i++) {
            if (prestamos[i] == prestamo) {
                prestamos[i] = prestamos[--numPrestamos];
                prestamos[numPrestamos] = null;
                break;
            }
        }
    }

    public Autor[] getAutores() {
        Autor[] resultado = new Autor[numAutores];
        System.arraycopy(autores, 0, resultado, 0, numAutores);
        return resultado;
    }

    public Libro[] getLibros() {
        Libro[] resultado = new Libro[numLibros];
        System.arraycopy(libros, 0, resultado, 0, numLibros);
        return resultado;
    }

    public Usuario[] getUsuarios() {
        Usuario[] resultado = new Usuario[numUsuarios];
        System.arraycopy(usuarios, 0, resultado, 0, numUsuarios);
        return resultado;
    }

    public Prestamo[] getPrestamos() {
        Prestamo[] resultado = new Prestamo[numPrestamos];
        System.arraycopy(prestamos, 0, resultado, 0, numPrestamos);
        return resultado;
    }

    public Libro[] buscarLibrosPorTitulo(String titulo) {
        Libro[] resultado = new Libro[numLibros];
        int contador = 0;
        for (int i = 0; i < numLibros; i++) {
            if (libros[i].getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado[contador++] = libros[i];
            }
        }
        Libro[] librosEncontrados = new Libro[contador];
        System.arraycopy(resultado, 0, librosEncontrados, 0, contador);
        return librosEncontrados;
    }

    public Usuario[] buscarUsuariosPorNombre(String nombre) {
        Usuario[] resultado = new Usuario[numUsuarios];
        int contador = 0;
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i].getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado[contador++] = usuarios[i];
            }
        }
        Usuario[] usuariosEncontrados = new Usuario[contador];
        System.arraycopy(resultado, 0, usuariosEncontrados, 0, contador);
        return usuariosEncontrados;
    }
}
