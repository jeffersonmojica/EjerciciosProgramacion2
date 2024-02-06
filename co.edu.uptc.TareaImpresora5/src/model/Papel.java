package model;


public class Papel {
    private int cantidadHojas;

    public Papel(int cantidadInicial) {
        this.cantidadHojas = cantidadInicial;
    }

    public int getCantidadHojas() {
        return cantidadHojas;
    }

    public void reducirHojas(int cantidad) {
        cantidadHojas -= cantidad;
        if (cantidadHojas < 0) {
            cantidadHojas = 0;
        }
    }

    public void rellenarHojas(int cantidad) {
        cantidadHojas += cantidad;
        System.out.println("Hojas rellenadas: " + cantidad);
    }
}
