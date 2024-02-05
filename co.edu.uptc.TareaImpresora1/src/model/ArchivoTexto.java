package model;


public class ArchivoTexto extends ArchivoImprimir {
    public ArchivoTexto(String nombre, int paginas, boolean dobleCara, boolean color, String tamañoHoja) {
        super(nombre, paginas, dobleCara, color, tamañoHoja);
    }

    @Override
    public void realizarOperacionEspecifica() {
        System.out.println("Operación específica para archivos de texto");
    }
}