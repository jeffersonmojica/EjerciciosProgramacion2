package model;

	
	public class ArchivoVideo extends ArchivoImprimir {
	    public ArchivoVideo(String nombre, int paginas, boolean dobleCara, boolean color, String tamañoHoja) {
	        super(nombre, paginas, dobleCara, color, tamañoHoja);
	    }

	    @Override
	    public void realizarOperacionEspecifica() {
	        System.out.println("Operación específica para archivos de video");
	    }
	}

