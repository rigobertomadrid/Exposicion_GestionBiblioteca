package exposicion_gestionbibliotecas;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private ControladorBiblioteca controlador;

    public Main() {
        controlador = new ControladorBiblioteca();

        setTitle("Sistema de Biblioteca Digital");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color cafeClaro = new Color(210, 180, 140);
        Color cafeOscuro = new Color(139, 69, 19);
        Font fuenteTitulo = new Font("Serif", Font.BOLD, 26);
        Font fuenteBoton = new Font("Serif", Font.BOLD, 22);

        JLabel titulo = new JLabel("Biblioteca Digital", JLabel.CENTER);
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(cafeOscuro);

        JPanel contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new BorderLayout());
        contenedorPrincipal.setBackground(cafeClaro);
        
        contenedorPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(9, 1, 20, 20));
        panelBotones.setBackground(cafeClaro);

        JButton agregarlibro = new JButton("Agregar Libro");
        JButton buscarlibro = new JButton("Buscar Libro");
        JButton agregarusuario = new JButton("Agregar Autor");
        JButton registrarUsuario = new JButton("Registrar Usuario");
        JButton buscarUsuario = new JButton("Buscar Usuario");
        JButton registrarPrestamo = new JButton("Registrar Prestamo");
        JButton devolverLibro = new JButton("Devolver Libro");
        JButton verReportes = new JButton("Ver Reportes");
        JButton salir = new JButton("Salir");

        cofiBoton(agregarlibro, cafeOscuro, fuenteBoton, cafeClaro);
        cofiBoton(buscarlibro, cafeOscuro, fuenteBoton, cafeClaro);
        cofiBoton(agregarusuario, cafeOscuro, fuenteBoton, cafeClaro);
        cofiBoton(registrarUsuario, cafeOscuro, fuenteBoton, cafeClaro);
        cofiBoton(buscarUsuario, cafeOscuro, fuenteBoton, cafeClaro);
        cofiBoton(registrarPrestamo, cafeOscuro, fuenteBoton, cafeClaro);
        cofiBoton(devolverLibro, cafeOscuro, fuenteBoton, cafeClaro);
        cofiBoton(verReportes, cafeOscuro, fuenteBoton, cafeClaro);
        cofiBoton(salir, cafeOscuro, fuenteBoton, cafeClaro);

        panelBotones.add(agregarlibro);
        panelBotones.add(buscarlibro);
        panelBotones.add(agregarusuario);
        panelBotones.add(registrarUsuario);
        panelBotones.add(buscarUsuario);
        panelBotones.add(registrarPrestamo);
        panelBotones.add(devolverLibro);
        panelBotones.add(verReportes);
        panelBotones.add(salir);

        JPanel panelNormal = new JPanel();
        panelNormal.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelNormal.setBackground(cafeClaro);
        panelNormal.add(panelBotones);

        contenedorPrincipal.add(panelNormal, BorderLayout.CENTER);

        add(contenedorPrincipal);

        agregarlibro.addActionListener(e -> {
            try {
                if (!controlador.agregarLibro()) {
                    mostrarMensaje("No se pudo agregar el libro.");
                }
            } catch (Exception ex) {
                manejarError("Error al agregar libro", ex);
            }
        });

        buscarlibro.addActionListener(e -> {
            try {
                if (!controlador.buscarLibro()) {
                    mostrarMensaje("No hay libros agregados aun.");
                }
            } catch (Exception ex) {
                manejarError("Error al buscar libro", ex);
            }
        });

        agregarusuario.addActionListener(e -> {
            try {
                if (!controlador.agregarAutor()) {
                    mostrarMensaje("No se pudo agregar el autor.");
                }
            } catch (Exception ex) {
                manejarError("Error al agregar autor", ex);
            }
        });

        registrarUsuario.addActionListener(e -> {
            try {
                if (!controlador.registrarUsuario()) {
                    mostrarMensaje("No se pudo registrar el usuario.");
                }
            } catch (Exception ex) {
                manejarError("Error al registrar usuario", ex);
            }
        });

        buscarUsuario.addActionListener(e -> {
            try {
                if (!controlador.buscarUsuario()) {
                    mostrarMensaje("No hay usuarios agregados aun.");
                }
            } catch (Exception ex) {
                manejarError("Error al buscar usuario", ex);
            }
        });

        registrarPrestamo.addActionListener(e -> {
            try {
                if (!controlador.registrarPrestamo()) {
                    mostrarMensaje("No se pudo registrar el prestamo.");
                }
            } catch (Exception ex) {
                manejarError("Error al registrar prestamo", ex);
            }
        });

        devolverLibro.addActionListener(e -> {
            try {
                if (!controlador.devolverLibro()) {
                    mostrarMensaje("No se pudo devolver el libro.");
                }
            } catch (Exception ex) {
                manejarError("Error al devolver libro", ex);
            }
        });

        verReportes.addActionListener(e -> {
            try {
                controlador.verReportes();
            } catch (Exception ex) {
                manejarError("Error al ver reportes", ex);
            }
        });

        salir.addActionListener(e -> {
            controlador.guardarDatos();
            System.exit(0);
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controlador.guardarDatos();
            }
        });
    }

    private void cofiBoton(JButton boton, Color colorFondo, Font fuente, Color colorTexto) {
        boton.setPreferredSize(new Dimension(300, 50));
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
    }

    private void manejarError(String mensaje, Exception ex) {
        JOptionPane.showMessageDialog(this, mensaje + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
