package model;

import java.util.LinkedList;
import java.util.Queue;

public class Impresora {
    private int hojasCarta;
    private int hojasOficio;
    private Queue<ArchivoImprimir> colaImpresion;

    public Impresora(int hojasCarta, int hojasOficio) {
        this.hojasCarta = hojasCarta;
        this.hojasOficio = hojasOficio;
        this.colaImpresion = new LinkedList<>();
    }

    public void agregarColaImpresion(ArchivoImprimir archivoImprimir) {
        colaImpresion.add(archivoImprimir);
    }

    public void imprimir() throws SinHojasException {
        if (!colaImpresion.isEmpty()) {
            ArchivoImprimir archivoImprimir = colaImpresion.poll();
            try {
                archivoImprimir.realizarOperacionEspecifica();

                if (archivoImprimir.getTamañoHoja().equals("carta")) {
                    validarHojas(archivoImprimir.getPaginas(), hojasCarta, "hojas de carta");
                    hojasCarta -= archivoImprimir.getPaginas();
                } else if (archivoImprimir.getTamañoHoja().equals("oficio")) {
                    validarHojas(archivoImprimir.getPaginas(), hojasOficio, "hojas de oficio");
                    hojasOficio -= archivoImprimir.getPaginas();
                }

                System.out.println("Impresión completada");
                actualizarEstado();  // Agregamos esto para actualizar el estado después de imprimir
            } catch (SinHojasException e) {
                System.out.println(e.getMessage());
                colaImpresion.add(archivoImprimir);
            }
        } else {
            System.out.println("La cola de impresión está vacía.");
        }
    }

    private void validarHojas(int paginas, int hojasDisponibles, String tipoHojas) throws SinHojasException {
        if (paginas > hojasDisponibles) {
            throw new SinHojasException("Error: No hay suficientes " + tipoHojas + ".");
        }
    }

    public void recargarHojas() {
        // Validamos para evitar que el programa se dañe con una cantidad excesiva de hojas
        if (hojasCarta + hojasOficio >= 2000) {
            System.out.println("Error: La cantidad total de hojas no puede ser mayor o igual a 2000.");
        } else {
            hojasCarta = 100; // Cantidad de hojas carta después de recargar
            hojasOficio = 50; // Cantidad de hojas oficio después de recargar
            System.out.println("Se recargaron las hojas de la impresora.");
            actualizarEstado();  // Agregamos esto para actualizar el estado después de recargar
        }
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

    private void actualizarEstado() {
        String estado = "Hojas carta: " + hojasCarta +
                ", Hojas oficio: " + hojasOficio +
                ", Archivos en cola: " + colaImpresion.size();
        System.out.println(estado);
    }
}
