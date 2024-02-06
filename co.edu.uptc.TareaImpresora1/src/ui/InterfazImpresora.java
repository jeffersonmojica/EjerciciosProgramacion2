package ui;
import javax.swing.JOptionPane;

import logic.GestorImpresora;

public class InterfazImpresora {
    public static void main(String[] args) {
        GestorImpresora gestorImpresora = new GestorImpresora();
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
        }
    }

	public void actualizarSalida(String estado) {
		// TODO Auto-generated method stub
		
	}

	public void inicializar() {
		// TODO Auto-generated method stub
		
	}
}
