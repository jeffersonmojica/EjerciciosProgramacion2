package ui;
//Capa de Interfaz (interfaz gráfica)
import javax.swing.*;

import logic.ImpresoraService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImpresoraSimulador {
 private JFrame frame;
 private ImpresoraService impresoraService;

 public ImpresoraSimulador() {
     impresoraService = new ImpresoraService(100);
     initialize();
 }

 private void initialize() {
     frame = new JFrame("Simulador de Impresora");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(600, 400);

     JPanel panel = new JPanel();
     panel.setLayout(new GridLayout(4, 2));

     JButton btnAgregarDocumento = new JButton("Agregar Documento");
     JButton btnImprimirDocumentos = new JButton("Imprimir Documentos");
     JButton btnConsultarCola = new JButton("Consultar Cola");
     JButton btnConsultarEnProceso = new JButton("Consultar En Proceso");
     JButton btnConsultarImpresos = new JButton("Consultar Impresos");
     JButton btnConsultarHojasCarta = new JButton("Consultar Hojas Carta");
     JButton btnConsultarHojasOficio = new JButton("Consultar Hojas Oficio");
     JButton btnSalir = new JButton("Salir");

     btnAgregarDocumento.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             agregarDocumento();
         }
     });

     btnImprimirDocumentos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             imprimirDocumentos();
             actualizarEstados();
         }
     });

     btnConsultarCola.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             consultarCola();
         }
     });

   

     btnConsultarImpresos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             consultarImpresos();
         }
     });

     btnConsultarHojasCarta.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             consultarHojas("carta");
         }
     });

     btnConsultarHojasOficio.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             consultarHojas("oficio");
         }
     });

     btnSalir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             System.exit(0);
         }
     });

     panel.add(btnAgregarDocumento);
     panel.add(btnImprimirDocumentos);
     panel.add(btnConsultarCola);
     panel.add(btnConsultarEnProceso);
     panel.add(btnConsultarImpresos);
     panel.add(btnConsultarHojasCarta);
     panel.add(btnConsultarHojasOficio);
     panel.add(btnSalir);

     frame.getContentPane().add(BorderLayout.CENTER, panel);
     frame.setVisible(true);
 }

 private void agregarDocumento() {
     String nombre = JOptionPane.showInputDialog("Ingrese el nombre del documento:");
     int numeroHojas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de hojas:"));
     String tamano = JOptionPane.showInputDialog("Ingrese el tamaño del papel (oficio/carta):");
     String tipoArchivo = JOptionPane.showInputDialog("Ingrese el tipo de archivo (texto/imagen/musica/video):");

     if (!validarTipoArchivo(tipoArchivo)) {
         JOptionPane.showMessageDialog(frame, "Tipo de archivo no válido para impresión. No se puede agregar a la cola.");
         return;
     }

     boolean dobleCara = Boolean.parseBoolean(JOptionPane.showInputDialog("¿Imprimir a doble cara? (true/false):"));
     boolean blancoYNegro = Boolean.parseBoolean(JOptionPane.showInputDialog("¿Imprimir en blanco y negro? (true/false):"));

     impresoraService.agregarDocumento(nombre, numeroHojas, tamano, tipoArchivo, dobleCara, blancoYNegro);
 }

 private boolean validarTipoArchivo(String tipoArchivo) {
     return tipoArchivo.equals("texto") || tipoArchivo.equals("imagen");
 }

 private void imprimirDocumentos() {
     impresoraService.imprimirDocumentos();
 }

 private void consultarCola() {
     JOptionPane.showMessageDialog(frame, "Documentos en cola:\n" + impresoraService.obtenerDocumentosEnCola());
 }


 private void consultarImpresos() {
     JOptionPane.showMessageDialog(frame, "Documentos impresos:\n" + impresoraService.obtenerDocumentosImpresos());
 }

 private void consultarHojas(String tipoPapel) {
     int cantidadHojas = impresoraService.getCantidadHojas(tipoPapel);
     JOptionPane.showMessageDialog(frame, "Cantidad de hojas de " + tipoPapel + ": " + cantidadHojas);
 }

 private void actualizarEstados() {
     // Actualizar la interfaz gráfica con los estados actuales
     // Puedes implementar esta función según las necesidades específicas de tu interfaz
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
         public void run() {
             new ImpresoraSimulador();
         }
     });
 }
}
