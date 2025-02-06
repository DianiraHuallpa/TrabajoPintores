package PintoresV4;

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
                    this.cubo1.reservado();
                    this.cubo2.reservado();
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
                this.cubo1.liberado();
                this.cubo2.liberado();
                this.cubo1.notifyAll();
                this.cubo2.notifyAll();
            }
        }
    }

    public synchronized void mezclar() {
        Random r = new Random();
        try {
            while (true) {
                while(!this.reservar()) {
                    System.out.println("Pintor " + num + " esperando cubo "  + this.cubo1.getColor() + " y " + this.cubo2.getColor());
                    this.cubo1.wait();
                    this.cubo2.wait();
                }
                // MECLANDO
                System.out.println("Pintor " + num + " Mezclando " + this.color + " con " + this.cubo1.getColor() + " y " + this.cubo2.getColor() + " ...");
                Thread.sleep(r.nextInt(100, 500));
                liberar();
                System.out.println(res);
                this.notifyAll();
                Thread.sleep(r.nextInt(1000, 2000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.mezclar();
    }

    public static void main(String[] args) {
        Cubo cian = new Cubo("cian");
        Cubo magenta = new Cubo("magenta");
        Cubo amarillo = new Cubo("amarillo");
        Cubo marron = new Cubo("marron");
        Resultado res = new Resultado();

        Pintor p1 = new Pintor(1, cian, magenta, "azul", res);
        Pintor p2 = new Pintor(2, magenta, amarillo, "rojo", res);
        Pintor p3 = new Pintor(3, amarillo, marron, "verde", res);
        Pintor p4 = new Pintor(4, marron, cian, "rojizo", res);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}
