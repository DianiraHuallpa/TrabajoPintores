package PintoresManejador;

public class Cubo {
    private final String color;
    private boolean enUso = false;

    public Cubo(String color) {
        this.color = color;
    }

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    public String getColor() {
        return color;
    }
}