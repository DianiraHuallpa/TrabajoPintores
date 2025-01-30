package PintoresV3;

public class Resultado {
    private int azul;
    private int rojo;
    private int verde;
    private int tutifruti;

    public Resultado () {
        this.azul = 0;
        this.rojo = 0;
        this.verde = 0;
        this.tutifruti = 0;
    }

    public void add (String color) {
        switch (color) {
            case "azul": {
                this.azul++;
                break;
            }
            case "rojo": {
                this.rojo++;
                break;
            }
            case "verde": {
                this.verde++;
                break;
            }
            case "tutifruti": {
                this.tutifruti++;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Resultado {" +
                "azul=" + azul +
                ", rojo=" + rojo +
                ", verde=" + verde +
                ", tutifruti=" + tutifruti +
                '}';
    }
}
