package PintoresV3;

import java.util.Random;

public class Pintor extends Thread {
    private int num;
    private Cubo cubo1;
    private Cubo cubo2;
    private String color;
    private Resultado res;

    public Pintor(int num, Cubo cubo1, Cubo cubo2, String color, Resultado res) {
        this.num = num;
        this.cubo1 = cubo1;
        this.cubo2 = cubo2;
        this.color = color;
        this.res = res;
    }

    public boolean reservar() {
        synchronized (cubo1) {
            synchronized (cubo2) {
                if (!this.cubo1.getEnUso() && !this.cubo2.getEnUso()) {
                    this.cubo1.reservar();
                    this.cubo2.reservar();
                    return true;
                }
            }
        }
        return false;
    }

    public void liberar() {
        synchronized (cubo1) {
            synchronized (cubo2) {
                this.res.add(this.color);
                this.cubo1.liberar();
                this.cubo2.liberar();
            }
        }
    }

    @Override
    public void run() {
        Random r = new Random();
        try {
            while (true) {
                if (reservar()) {
                    // MECLANDO
                    System.out.println("Pintor " + num + " Mezclando " + this.color + " ...");
                    Thread.sleep(r.nextInt(100, 500));
                    liberar();
                    System.out.println(res);
                    Thread.sleep(r.nextInt(1000, 2000));
                } else {
                    // ESPERANDO REINTENTO
                    Thread.sleep(r.nextInt(100, 2000));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cubo cian = new Cubo("cian");
        Cubo magenta = new Cubo("magenta");
        Cubo amarillo = new Cubo("amarillo");
        Cubo caca = new Cubo("caca");
        Resultado res = new Resultado();

        Pintor p1 = new Pintor(1, cian, magenta, "azul", res);
        Pintor p2 = new Pintor(2, magenta, amarillo, "rojo", res);
        Pintor p3 = new Pintor(3, amarillo, caca, "verde", res);
        Pintor p4 = new Pintor(4, caca, cian, "tutifruti", res);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}
