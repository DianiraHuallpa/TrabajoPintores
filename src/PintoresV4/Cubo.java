package PintoresV4;

public class Cubo {
    private String color;
    private boolean enUso;

    public Cubo(String color) {
        this.color = color;
        this.enUso = false;
    }

    public String getColor() {
        return this.color;
    }
    public boolean getEnUso() {
        return this.enUso;
    }

    public void reservado() {
        this.enUso = true;
    }

    public void liberado() {
        this.enUso = false;
    }
}
