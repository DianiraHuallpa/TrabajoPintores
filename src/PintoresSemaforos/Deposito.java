package PintoresSemaforos;
import java.util.concurrent.Semaphore;

public class Deposito {
    private final String color;
    private final Semaphore semaforo = new Semaphore(1);

    public Deposito(String color) {
        this.color = color;
    }

    public void reservar() throws InterruptedException {
        semaforo.acquire();
    }

    public void liberar() {
        semaforo.release();
    }

    public String getColor() {
        return color;
    }
}