package PintoresV2;

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
//evitar que otro pintor use los mismos cubos al mismo tiempo
    public boolean reservar() {
        synchronized (cubo1) {//
            synchronized (cubo2) {
                if (!this.cubo1.getEnUso() && !this.cubo2.getEnUso()) {
                    this.cubo1.reservado();
                    this.cubo2.reservado();
                    return true;
                }
            }
        }
        return false;//Si no, sale y desbloquea los cubos automáticamente.
    }
    //Bloqueamos los cubos que queremos liberar para liberarlos
    public void liberar() {
        synchronized (cubo1) {
            synchronized (cubo2) {
                this.res.add(this.color);
                this.cubo1.liberado();
                this.cubo2.liberado();
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
                    System.out.println("Pintor " + num + " Mezclando " + this.color + " con " + this.cubo1.getColor() + " y " + this.cubo2.getColor() + " ...");
                    Thread.sleep(r.nextInt(100, 500));//tiempo de trabajo de mezcla

                    liberar();//cubos disponibles para otros pintores

                    System.out.println(res);
                    Thread.sleep(r.nextInt(1000, 2000));//Descansa antes de volver a intentar mezclar
                } else {
                    // ESPERANDO REINTENTO
                    System.out.println("Pintor " + num + " esperando cubo "  + this.cubo1.getColor() + " y " + this.cubo2.getColor());
                    Thread.sleep(r.nextInt(100, 500));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//creamos colores
        Cubo cian = new Cubo("cian");
        Cubo magenta = new Cubo("magenta");
        Cubo amarillo = new Cubo("amarillo");
//creamos el contador de mezclas
        Resultado res = new Resultado();
// Creamos 3 pintores
        Pintor p1 = new Pintor(1, cian, magenta, "azul", res);
        Pintor p2 = new Pintor(2, magenta, amarillo, "rojo", res);
        Pintor p3 = new Pintor(3, amarillo, cian, "verde", res);

        p1.start();
        p2.start();
        p3.start();

    }
}






//Como cada pintor descansa un tiempo aleatorio antes de reintentar, no siempre intentarán reservar los cubos al mismo tiempo.
//Al dormir aleatoriamente, los hilos tienen más oportunidad de acceder en distintos momentos.
