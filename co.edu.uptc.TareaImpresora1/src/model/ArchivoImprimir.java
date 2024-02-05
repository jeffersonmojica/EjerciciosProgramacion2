package model;

public abstract class ArchivoImprimir {
    private String nombre;
    private int paginas;
    private boolean dobleCara;
    private boolean color;
    private String tamañoHoja;

    public ArchivoImprimir(String nombre, int paginas, boolean dobleCara, boolean color, String tamañoHoja) {
        this.nombre = nombre;
        this.paginas = paginas;
        this.dobleCara = dobleCara;
        this.color = color;
        this.tamañoHoja = tamañoHoja;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPaginas() {
        return paginas;
    }

    public boolean isDobleCara() {
        return dobleCara;
    }

    public boolean isColor() {
        return color;
    }

    public String getTamañoHoja() {
        return tamañoHoja;
    }

    public abstract void realizarOperacionEspecifica();
}
