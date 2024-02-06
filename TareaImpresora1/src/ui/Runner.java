package ui;

import logic.GestorImpresora;
import ui.InterfazImpresora;

public class Runner {
	public static void main(String[] args) {
		// Crear un objeto GestorImpresora (suponiendo que ya está implementado)
		GestorImpresora gestorImpresora = new GestorImpresora();

		// Crear una instancia de InterfazImpresora y pasarle el GestorImpresora
		InterfazImpresora interfazImpresora = new InterfazImpresora(gestorImpresora);

		// Actualizar el estado inicial de la interfaz
		interfazImpresora.actualizarEstado();
	}
}
