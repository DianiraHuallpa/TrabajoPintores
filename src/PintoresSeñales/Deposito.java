package PintoresSeñales;

public class Deposito {
    private final String color;
    private boolean enUso = false;

    public Deposito(String color) {
        this.color = color;
    }

    public synchronized void tomar() throws InterruptedException {
        while (enUso) {
            wait();
        }
        enUso = true;
    }

    public synchronized void liberar() {
        enUso = false;
        notifyAll();
    }

    public String getColor() {
        return color;
    }
}