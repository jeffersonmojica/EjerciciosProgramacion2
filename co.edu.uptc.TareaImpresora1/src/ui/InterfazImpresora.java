package ui;

import javax.swing.*;

import logic.GestorImpresora;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        inicializar();
    }

    public void inicializar() {
        frame = new JFrame("Simulador de Impresora");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBounds(10, 10, 465, 200);
        frame.add(scrollPane);

        botonAgregar = crearBoton("Agregar Archivo", 10, 220, 150, 30, new Color(255, 140, 0));
        botonAgregar.addActionListener(e -> mostrarDialogoAgregarArchivo());
        frame.add(botonAgregar);

        botonImprimir = crearBoton("Imprimir", 180, 220, 150, 30, new Color(255, 140, 0));
        botonImprimir.addActionListener(e -> {
            gestorImpresora.imprimir();
            actualizarEstado();
        });
        frame.add(botonImprimir);

        botonRecargar = crearBoton("Recargar Papel", 350, 220, 150, 30, new Color(255, 140, 0));
        botonRecargar.addActionListener(e -> {
            gestorImpresora.recargarHojas();
            actualizarEstado();
        });
        frame.add(botonRecargar);

        botonSalir = crearBoton("Salir", 175, 300, 150, 30, new Color(178, 34, 34));
        botonSalir.addActionListener(e -> cerrarAplicacion());
        frame.add(botonSalir);

        // Cambia el color de fondo a gris oscuro
        frame.getContentPane().setBackground(new Color(169, 169, 169));

        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    private void cerrarAplicacion() {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);  // Cierra la aplicación
        }
    }

    private JButton crearBoton(String texto, int x, int y, int ancho, int alto, Color color) {
        JButton boton = new JButton(texto);
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
                ", Archivos en cola: " + gestorImpresora.getTamañoColaImpresion();
        actualizarSalida(estado);
    }
}
