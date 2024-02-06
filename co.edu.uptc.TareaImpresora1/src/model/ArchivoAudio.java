package model;

public class ArchivoAudio extends ArchivoImprimir {
    public ArchivoAudio(String nombre, int paginas, boolean dobleCara, boolean color, String tamañoHoja) {
        super(nombre, paginas, dobleCara, color, tamañoHoja);
    }

    @Override
    public void realizarOperacionEspecifica() {
        System.out.println("Operación específica para archivos de audio");
    }
}
