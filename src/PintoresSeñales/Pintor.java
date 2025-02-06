package PintoresSeñales;

public class Pintor extends Thread {
    private final Deposito deposito1;
    private final Deposito deposito2;
    private final String nombre;

    public Pintor(String nombre, Deposito d1, Deposito d2) {
        this.nombre = nombre;
        this.deposito1 = d1;
        this.deposito2 = d2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                deposito1.tomar();
                System.out.println(nombre + " reserva " + deposito1.getColor());
                deposito2.tomar();
                System.out.println(nombre + " reserva " + deposito2.getColor());
                System.out.println(nombre + " está mezclando " + deposito1.getColor() + " y " + deposito2.getColor());
                Thread.sleep(4000); // Simula el tiempo de mezcla
                System.out.println(nombre + " libera " + deposito2.getColor());
                deposito2.liberar();
                System.out.println(nombre + " libera " + deposito1.getColor());
                deposito1.liberar();
                // Descanso antes de intentar de nuevo
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Deposito cian = new Deposito("Cian");
        Deposito magenta = new Deposito("Magenta");
        Deposito amarillo = new Deposito("Amarillo");

        Pintor pintor1 = new Pintor("Pintor 1", cian, magenta);
        Pintor pintor2 = new Pintor("Pintor 2", amarillo, magenta);
        Pintor pintor3 = new Pintor("Pintor 3", amarillo, cian);

        pintor1.start();
        pintor2.start();
        pintor3.start();
    }
}
