package PintoresV1;

import java.util.Random;

public class Pintor extends Thread {
    private int num;
    private Cubo cubo1;
    private Cubo cubo2;
    private String color;

    public Pintor(int num, Cubo cubo1, Cubo cubo2, String color) {
        this.num = num;
        this.cubo1 = cubo1;
        this.cubo2 = cubo2;
        this.color = color;
    }

    public boolean reservar() {
        synchronized (cubo1) {
            synchronized (cubo2) {
                if (this.cubo1.getEnUso() && this.cubo2.getEnUso()) {
                    this.cubo1.reservar();
                    this.cubo2.reservar();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean liberar() {
        synchronized (cubo1) {
            synchronized (cubo2) {
                this.cubo1.liberar();
                this.cubo2.liberar();
            }
        }
        return false;
    }

    public void pintar() {
        Random r = new Random();
        try {
            boolean terminado = false;
            while (!terminado) {
                if (reservar()) {
                    System.out.println("Pintor " + num + " Mezclando " + this.color + " ...");
                    Thread.sleep(r.nextInt(1000, 5000));
                    System.out.println(this.color + "Mezclado, Pintor " + num + " Descansando");
                    liberar();
                    terminado = true;
                } else {
                    System.out.println("Pintor " + num + " Durmiendo ...");
                    Thread.sleep(r.nextInt(1000, 2000));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.pintar();
    }
}
