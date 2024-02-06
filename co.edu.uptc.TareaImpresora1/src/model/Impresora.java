package model;

import java.util.LinkedList;
import java.util.Queue;
import ui.InterfazImpresora;

public class Impresora {
    private int hojasCarta;
    private int hojasOficio;
    private Queue<ArchivoImprimir> colaImpresion;
    private InterfazImpresora interfazImpresora;

    public Impresora(int hojasCarta, int hojasOficio) {
        this.hojasCarta = hojasCarta;
        this.hojasOficio = hojasOficio;
        this.colaImpresion = new LinkedList<>();
    }

    public void agregarColaImpresion(ArchivoImprimir archivoImprimir) {
        colaImpresion.add(archivoImprimir);
    }

    public void imprimir() {
        if (!colaImpresion.isEmpty()) {
            ArchivoImprimir archivoImprimir = colaImpresion.poll();
            try {
                if (archivoImprimir instanceof ArchivoTexto || archivoImprimir instanceof ArchivoImagen) {
                    actualizarSalida("Imprimiendo: " + archivoImprimir.getNombre());
                    if (archivoImprimir.isColor()) {
                        actualizarSalida("Imprimiendo a color");
                    } else {
                        actualizarSalida("Imprimiendo en blanco y negro");
                    }
                    if (archivoImprimir.isDobleCara()) {
                        actualizarSalida("Imprimiendo a doble cara");
                    } else {
                        actualizarSalida("Imprimiendo a una sola cara");
                    }
                    if (archivoImprimir.getTamañoHoja().equals("carta")) {
                        if (archivoImprimir.getPaginas() <= hojasCarta) {
                            hojasCarta -= archivoImprimir.getPaginas();
                        } else {
                            throw new SinHojasException("Error: No hay suficientes hojas de carta.");
                        }
                    } else if (archivoImprimir.getTamañoHoja().equals("oficio")) {
                        if (archivoImprimir.getPaginas() <= hojasOficio) {
                            hojasOficio -= archivoImprimir.getPaginas();
                        } else {
                            throw new SinHojasException("Error: No hay suficientes hojas de oficio.");
                        }
                    }
                    actualizarSalida("Impresión completada");
                } else {
                    throw new SinHojasException("Error: No se puede imprimir archivos de audio o video.");
                }
            } catch (SinHojasException e) {
                actualizarSalida(e.getMessage());
                colaImpresion.add(archivoImprimir);
            }
        } else {
            actualizarSalida("La cola de impresión está vacía.");
        }
    }

    public void recargarHojas() {
        hojasCarta = 100; // Cantidad de hojas carta después de recargar
        hojasOficio = 50; // Cantidad de hojas oficio después de recargar
        actualizarSalida("Se recargaron las hojas de la impresora.");
    }

    public int getHojasCarta() {
        return hojasCarta;
    }

    public int getHojasOficio() {
        return hojasOficio;
    }

    public int getTamañoColaImpresion() {
        return colaImpresion.size();
    }

    public void setInterfazImpresora(InterfazImpresora interfazImpresora) {
        this.interfazImpresora = interfazImpresora;
    }

    private void actualizarSalida(String mensaje) {
        if (interfazImpresora != null) {
            interfazImpresora.actualizarSalida(mensaje);
        }
    }
}
