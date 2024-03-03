package model;

public class ArchivoImprimir {
    private String nombre;
    private String tamanoHoja;
    private int paginas;

    public ArchivoImprimir(String nombre, String tamanoHoja, int paginas) {
        this.nombre = nombre;
        this.tamanoHoja = tamanoHoja;
        this.paginas = paginas;
    }

    public String getNombre() {
        return nombre;
    }

    public String gettamanoHoja() {
        return tamanoHoja;
    }

    public int getPaginas() {
        return paginas;
    }

    public void realizarOperacionEspecifica() {
        // Realizar operación específica del archivo (si es necesario)
    }
}
