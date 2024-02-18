package ui;
import javax.swing.JOptionPane;

import logic.GestorImpresora;

public class InterfazImpresora {
public static void main(String[] args) {
	
}        GestorImpresora gestorImpresora = new GestorImpresora();
        while (true) {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Seleccione una opción:\n" +
                            "1. Agregar Archivo\n" +
                            "2. Imprimir\n" +
                            "3. Recargar Papel\n" +
                            "4. Salir"));

            switch (opcion) {
                case 1:
                    gestorImpresora.agregarArchivo();
                    break;
                case 2:
                    gestorImpresora.imprimir();
                    break;
                case 3:
                    gestorImpresora.recargarHojas();
                    break;
                case 4:
                    System.exit(0);
            }
=======
    public InterfazImpresora(GestorImpresora gestorImpresora) {
        this.gestorImpresora = gestorImpresora;
        inicializar();
    }
    public static void main(String[] args) {
        GestorImpresora gestorImpresora = new GestorImpresora();
        gestorImpresora.iniciarInterfaz();
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

	public void actualizarSalida(String estado) {
		// TODO Auto-generated method stub
		
	}

	public void inicializar() {
		// TODO Auto-generated method stub
		
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
}
