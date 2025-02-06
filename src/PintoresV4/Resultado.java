package PintoresV4;

public class Resultado {
    private int azul;
    private int rojo;
    private int verde;
    private int rojizo;

    public Resultado () {
        this.azul = 0;
        this.rojo = 0;
        this.verde = 0;
        this.rojizo = 0;
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
            case "rojizo": {
                this.rojizo++;
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
                ", rojizo=" + rojizo +
                '}';
    }
}
