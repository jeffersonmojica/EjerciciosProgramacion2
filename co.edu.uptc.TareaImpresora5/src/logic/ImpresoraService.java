package logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.ArchivoDocumento;
import model.ConfiguracionImpresora;
import model.Papel;

public class ImpresoraService {
    private Queue<ArchivoDocumento> colaDeImpresion;
    private Papel papelOficio;
    private Papel papelCarta;
    private List<ArchivoDocumento> documentosImpresos;

    public ImpresoraService(int cantidadInicialPapel) {
        colaDeImpresion = new LinkedList<>();
        papelOficio = new Papel(cantidadInicialPapel);
        papelCarta = new Papel(cantidadInicialPapel);
        documentosImpresos = new ArrayList<>();
    }

    public void agregarDocumento(String nombre, int numeroHojas, String tamano,
                                 String tipoArchivo, boolean dobleCara, boolean blancoYNegro) {
        if (!validarTipoArchivo(tipoArchivo)) {
            System.out.println("Tipo de archivo no válido para impresión. No se puede agregar a la cola.");
            return;
        }

        ConfiguracionImpresora configuracionImpresora = new ConfiguracionImpresora(dobleCara, blancoYNegro);
        ArchivoDocumento documento = new ArchivoDocumento(nombre, numeroHojas, tamano, tipoArchivo, configuracionImpresora);
        colaDeImpresion.add(documento);
        System.out.println("Documento agregado a la cola de impresión: " + documento.getNombre());
    }

    private boolean validarTipoArchivo(String tipoArchivo) {
        return tipoArchivo.equals("texto") || tipoArchivo.equals("imagen");
    }

    public void imprimirDocumentos() {
        if (colaDeImpresion.isEmpty()) {
            System.out.println("No hay documentos para imprimir.");
            return;
        }

        // Verificar si hay suficientes hojas de papel antes de imprimir
        if (!haySuficientesHojas()) {
            System.out.println("No hay suficientes hojas de papel para imprimir.");
            return;
        }

        System.out.println("Documentos en cola:");

        for (ArchivoDocumento documento : colaDeImpresion) {
            System.out.println("Nombre: " + documento.getNombre());
        }

        System.out.println("Imprimiendo documentos:");

        while (!colaDeImpresion.isEmpty()) {
            ArchivoDocumento documento = colaDeImpresion.poll();
            int numeroHojas = documento.getNumeroHojas();
            ConfiguracionImpresora configuracionImpresora = documento.getConfiguracionImpresora();

            if (configuracionImpresora.isDobleCara()) {
                numeroHojas *= 2;
            }

            if (!reducirHojasPapel(numeroHojas, configuracionImpresora.isBlancoYNegro())) {
                System.out.println("No hay suficientes hojas de papel para imprimir.");
                break;
            }

            documentosImpresos.add(documento);
            System.out.println("Imprimiendo documento: " + documento.getNombre());
        }

        System.out.println("Todos los documentos han sido impresos.");
    }

    private boolean haySuficientesHojas() {
        for (ArchivoDocumento documento : colaDeImpresion) {
            int numeroHojas = documento.getNumeroHojas();
            ConfiguracionImpresora configuracionImpresora = documento.getConfiguracionImpresora();

            if (configuracionImpresora.isDobleCara()) {
                numeroHojas *= 2;
            }

            if (configuracionImpresora.isBlancoYNegro()) {
                if (numeroHojas > papelOficio.getCantidadHojas() || numeroHojas > papelCarta.getCantidadHojas()) {
                    return false;
                }
            } else {
                if (numeroHojas > papelCarta.getCantidadHojas()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean reducirHojasPapel(int numeroHojas, boolean blancoYNegro) {
        if (blancoYNegro) {
            if (numeroHojas > papelOficio.getCantidadHojas() || numeroHojas > papelCarta.getCantidadHojas()) {
                return false; // No hay suficientes hojas de papel
            }
        } else {
            if (numeroHojas > papelCarta.getCantidadHojas()) {
                return false; // No hay suficientes hojas de papel
            }
        }

        // Reducir las hojas de papel disponibles
        if (blancoYNegro) {
            papelOficio.reducirHojas(numeroHojas);
            papelCarta.reducirHojas(numeroHojas);
        } else {
            papelCarta.reducirHojas(numeroHojas);
        }
        return true;
    }

    public String obtenerDocumentosEnCola() {
        StringBuilder documentos = new StringBuilder();
        for (ArchivoDocumento documento : colaDeImpresion) {
            documentos.append("Nombre: ").append(documento.getNombre()).append("\n");
        }
        return documentos.toString();
    }

    public String obtenerDocumentosImpresos() {
        StringBuilder documentos = new StringBuilder();
        for (ArchivoDocumento documento : documentosImpresos) {
            documentos.append("Nombre: ").append(documento.getNombre()).append("\n");
        }
        return documentos.toString();
    }

    public int getCantidadHojas(String tipoPapel) {
        if (tipoPapel.equals("carta")) {
            return papelCarta.getCantidadHojas();
        } else if (tipoPapel.equals("oficio")) {
            return papelOficio.getCantidadHojas();
        } else {
            return 0;
        }
    }

    public void rellenarHojasImpresora(String tipoPapel, int cantidadHojas) {
        if (tipoPapel.equals("carta")) {
            papelCarta.rellenarHojas(cantidadHojas);
        } else if (tipoPapel.equals("oficio")) {
            papelOficio.rellenarHojas(cantidadHojas);
        }
    }
}
