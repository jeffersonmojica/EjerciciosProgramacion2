package ui;
import javax.swing.*;

import logic.GestorImpresora;
import model.SinHojasException;

import java.awt.*;
import java.io.File;
//aaa
public class InterfazImpresora {
    private JFrame frame;
    private JTextArea areaTexto;
    private JButton botonAgregar;
    private JButton botonImprimir;
    private JButton botonRecargar;
    private JButton botonSalir;
    private GestorImpresora gestorImpresora;

    public InterfazImpresora(GestorImpresora gestorImpresora) {
        this.gestorImpresora = gestorImpresora;
    }

    public void inicializar() {
        frame = new JFrame("IMPRESORA SIMULADOR");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        //LOGO
        ImageIcon frameIcon = new ImageIcon(getClass().getResource("/imagenes/ImpresoraLogo.png"));
        frame.setIconImage(frameIcon.getImage());
        
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBounds(10, 10, 465, 200);
        frame.add(scrollPane);

        ImageIcon archivoIcon = new ImageIcon(getClass().getResource("/imagenes/Archivo1icon.png"));
        botonAgregar = crearBoton("Agregar Archivo", 10, 220, 150, 30, new Color(255, 140, 0), archivoIcon);
        botonAgregar.addActionListener(e -> mostrarDialogoAgregarArchivo());
        frame.add(botonAgregar);

        ImageIcon impresoraIcon = new ImageIcon(getClass().getResource("/imagenes/Impresora.png"));
        botonImprimir = crearBoton("Imprimir", 180, 220, 150, 30, new Color(255, 140, 0), impresoraIcon);
        botonImprimir.addActionListener(e -> {
            try {
                gestorImpresora.imprimir();
            } catch (SinHojasException sinHojasException) {
                sinHojasException.printStackTrace();
            }
            actualizarEstado();
        });
        frame.add(botonImprimir);

        ImageIcon papelIcon = new ImageIcon(getClass().getResource("/imagenes/Papel.png"));
        botonRecargar = crearBoton("Recargar Papel", 350, 220, 150, 30, new Color(255, 140, 0), papelIcon);
        botonRecargar.addActionListener(e -> {
            gestorImpresora.recargarHojas();
            actualizarEstado();
        });
        frame.add(botonRecargar);

        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/imagenes/Exit.png"));
        botonSalir = crearBoton("Salir", 175, 300, 150, 30, new Color(178, 34, 34,200), exitIcon);
        botonSalir.addActionListener(e -> cerrarAplicacion());
        frame.add(botonSalir);

        frame.getContentPane().setBackground(new Color(169, 169, 169));

        frame.setLayout(null);
        frame.setVisible(true);

        actualizarEstado();
    }

    private void cerrarAplicacion() {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private JButton crearBoton(String texto, int x, int y, int ancho, int alto, Color color, ImageIcon icono) {
        JButton boton = new JButton(texto, icono);
        boton.setBounds(x, y, ancho, alto);
        boton.setBackground(color);
        return boton;
    }

    private void mostrarDialogoAgregarArchivo() {
        gestorImpresora.agregarArchivo();
        actualizarEstado();
    }

    public void actualizarSalida(String mensaje) {
        areaTexto.append(mensaje + "\n");
    }

    public void actualizarEstado() {
        String estado = "Hojas carta: " + gestorImpresora.getHojasCarta() +
                ", Hojas oficio: " + gestorImpresora.getHojasOficio() +
                ", Archivos en cola: " + gestorImpresora.getTamanoColaImpresion();
        actualizarSalida(estado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorImpresora gestorImpresora = new GestorImpresora();
            InterfazImpresora interfaz = new InterfazImpresora(gestorImpresora);
            interfaz.inicializar();
        });
    }
}
