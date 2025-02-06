package PintoresManejador;

import java.util.Random;

public class Pintor extends Thread {
    private final Cubo cubo1;
    private final Cubo cubo2;
    private final String nombre;
    private final ManejadorReservas mj;

    public Pintor(String nombre, Cubo d1, Cubo d2, ManejadorReservas mj) {
        this.nombre = nombre;
        this.cubo1 = d1;
        this.cubo2 = d2;
        this.mj = mj;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (true) {
            try {
                mj.tomaPareja(cubo1, cubo2);
                System.out.println(nombre + " está mezclando " + cubo1.getColor() + " y " + cubo2.getColor());
                Thread.sleep(r.nextInt(100, 500));
                System.out.println(nombre + " libera " + cubo1.getColor() + " y " + cubo2.getColor());
                mj.liberarPareja(cubo1, cubo2);
                Thread.sleep(r.nextInt(1000, 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Cubo cian = new Cubo("Cian");
        Cubo magenta = new Cubo("Magenta");
        Cubo amarillo = new Cubo("Amarillo");

        ManejadorReservas mj = new ManejadorReservas();

        Pintor pintor1 = new Pintor("Pintor 1", cian, magenta, mj);
        Pintor pintor2 = new Pintor("Pintor 2", magenta, amarillo, mj);
        Pintor pintor3 = new Pintor("Pintor 3", amarillo, cian, mj);

        pintor1.start();
        pintor2.start();
        pintor3.start();
    }
}
