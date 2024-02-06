package logic;

import javax.swing.JOptionPane;
import model.ArchivoImprimir;
import model.Impresora;
import ui.InterfazImpresora;

import model.ArchivoTexto;
import model.ArchivoImagen;
import model.ArchivoAudio;
import model.ArchivoVideo;

public class GestorImpresora {
    private Impresora impresora;
    private InterfazImpresora interfazImpresora;

    public GestorImpresora() {
        this.impresora = new Impresora(100, 50);
        this.interfazImpresora = new InterfazImpresora(this);
        this.impresora.setInterfazImpresora(interfazImpresora);
    }

    public void agregarArchivo() {
        String[] opciones = {"Sí", "No"};
        int confirmacion = JOptionPane.showOptionDialog(
                null,
                "¿Desea agregar un nuevo archivo?",
                "Agregar Archivo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (confirmacion == 0) {  // Si elige "Sí"
            String[] opcionesArchivo = {"Texto", "Imagen", "Audio", "Video"};
            int seleccionTipoArchivo = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione el tipo de archivo:",
                    "Tipo de Archivo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesArchivo,
                    opcionesArchivo[0]);

            if (seleccionTipoArchivo >= 0) {
                String tipoArchivo = opcionesArchivo[seleccionTipoArchivo];

                String[] opcionesTamañoHoja = {"Carta", "Oficio"};
                int seleccionTamañoHoja = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione el tamaño de hoja:",
                        "Tamaño de Hoja",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesTamañoHoja,
                        opcionesTamañoHoja[0]);

                if (seleccionTamañoHoja >= 0) {
                    String tamañoHoja = opcionesTamañoHoja[seleccionTamañoHoja];

                    try {
                        int paginas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de páginas:"));

                        if (paginas <= 0) {
                            JOptionPane.showMessageDialog(null, "Número de páginas no válido", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        boolean dobleCara = JOptionPane.showConfirmDialog(null, "¿Imprimir a doble cara?", "Seleccione una opción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                        boolean color = JOptionPane.showConfirmDialog(null, "¿Imprimir a color?", "Seleccione una opción", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

                        ArchivoImprimir archivo = crearArchivo(tipoArchivo, paginas, dobleCara, color, tamañoHoja);
                        if (archivo != null) {
                            impresora.agregarColaImpresion(archivo);
                            actualizarEstado();
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Ingrese un número válido para la cantidad de páginas", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public void imprimir() {
        impresora.imprimir();
        actualizarEstado();
    }

    public void recargarHojas() {
        impresora.recargarHojas();
        actualizarEstado();
    }

    private void actualizarEstado() {
        String estado = "Hojas carta: " + impresora.getHojasCarta() +
                ", Hojas oficio: " + impresora.getHojasOficio() +
                ", Archivos en cola: " + impresora.getTamañoColaImpresion();
        interfazImpresora.actualizarSalida(estado);
    }



    public void iniciarInterfaz() {
        interfazImpresora.inicializar();
    }

    private ArchivoImprimir crearArchivo(String tipoArchivo, int paginas, boolean dobleCara, boolean color, String tamañoHoja) {
        ArchivoImprimir archivo = null;

        switch (tipoArchivo.toLowerCase()) {
            case "texto":
                archivo = new ArchivoTexto("Archivo de Texto", paginas, dobleCara, color, tamañoHoja);
                break;
            case "imagen":
                archivo = new ArchivoImagen("Archivo de Imagen", paginas, dobleCara, color, tamañoHoja);
                break;
            case "audio":
                archivo = new ArchivoAudio("Archivo de Audio", paginas, dobleCara, color, tamañoHoja);
                break;
            case "video":
                archivo = new ArchivoVideo("Archivo de Video", paginas, dobleCara, color, tamañoHoja);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de archivo no válido", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

        return archivo;
    }

	public String getHojasCarta() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHojasOficio() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTamañoColaImpresion() {
		// TODO Auto-generated method stub
		return null;
	}
//VERIFICACION COMMIT
}
