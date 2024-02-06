package model;
public class ArchivoDocumento {
    private String nombre;
    private int numeroHojas;
    private String tamano;
    private String tipoArchivo;
    private ConfiguracionImpresora configuracionImpresora;

    public ArchivoDocumento(String nombre, int numeroHojas, String tamano, String tipoArchivo, ConfiguracionImpresora configuracionImpresora) {
        this.nombre = nombre;
        this.numeroHojas = numeroHojas;
        this.tamano = tamano;
        this.tipoArchivo = tipoArchivo;
        this.configuracionImpresora = configuracionImpresora;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroHojas() {
        return numeroHojas;
    }

    public String getTamano() {
        return tamano;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public ConfiguracionImpresora getConfiguracionImpresora() {
        return configuracionImpresora;
    }
}