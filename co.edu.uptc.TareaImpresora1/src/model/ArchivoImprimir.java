package model;

public class ArchivoImprimir {
    private String nombre;
    private String tamañoHoja;
    private int paginas;

    public ArchivoImprimir(String nombre, String tamañoHoja, int paginas) {
        this.nombre = nombre;
        this.tamañoHoja = tamañoHoja;
        this.paginas = paginas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTamañoHoja() {
        return tamañoHoja;
    }

    public int getPaginas() {
        return paginas;
    }

    public void realizarOperacionEspecifica() {
        // Realizar operación específica del archivo (si es necesario)
    }
}
