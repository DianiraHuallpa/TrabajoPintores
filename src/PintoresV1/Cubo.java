package PintoresV1;

public class Cubo {
    private String color;
    private boolean enUso;

    public Cubo(String color) {
        this.color = color;
        this.enUso = false;
    }

    public boolean getEnUso() {
        return !this.enUso;
    }

    public void reservar() {
        this.enUso = true;
    }

    public void liberar() {
        this.enUso = false;
    }
}
