package model;

public class ConfiguracionImpresora {
    private boolean dobleCara;
    private boolean blancoYNegro;

    public ConfiguracionImpresora(boolean dobleCara, boolean blancoYNegro) {
        this.dobleCara = dobleCara;
        this.blancoYNegro = blancoYNegro;
    }

    public boolean isDobleCara() {
        return dobleCara;
    }

    public boolean isBlancoYNegro() {
        return blancoYNegro;
    }
}