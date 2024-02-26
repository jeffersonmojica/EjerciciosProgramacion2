package logic;

import model.ArchivoImprimir;
import model.Impresora;
import model.SinHojasException;
import ui.InterfazImpresora;

public class GestorImpresora {
    private Impresora impresora;

    public GestorImpresora() {
        this.impresora = new Impresora(100, 50);
    }

    public void agregarArchivo() {
        ArchivoImprimir archivoImprimir = new ArchivoImprimir("Documento", "carta", 10);
        impresora.agregarColaImpresion(archivoImprimir);
        System.out.println("Se agregó un archivo a la cola de impresión.");
    }

    public void imprimir() throws SinHojasException {
        impresora.imprimir();
    }

    public void recargarHojas() {
        impresora.recargarHojas();
    }

    public int getHojasCarta() {
        return impresora.getHojasCarta();
    }

    public int getHojasOficio() {
        return impresora.getHojasOficio();
    }

    public int getTamañoColaImpresion() {
        return impresora.getTamañoColaImpresion();
    }

    public static void main(String[] args) {
        GestorImpresora gestorImpresora = new GestorImpresora();
        gestorImpresora.iniciarInterfaz();
    }

    public void iniciarInterfaz() {
        InterfazImpresora interfazImpresora = new InterfazImpresora(this);
        interfazImpresora.inicializar();
    }
}
