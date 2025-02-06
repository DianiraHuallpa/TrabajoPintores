package PintoresSyncronized;
import java.util.concurrent.Semaphore;

public class Deposito {
    private final String color;

    public Deposito(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}