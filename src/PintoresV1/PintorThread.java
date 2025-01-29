package PintoresV1;

public class PintorThread {
    public static void main(String[] args) {
        Cubo cian = new Cubo("cian");
        Cubo magenta = new Cubo("magenta");
        Cubo amarillo = new Cubo("amarillo");

        Pintor p1 = new Pintor(1, cian, magenta, "azul");
        Pintor p2 = new Pintor(2, magenta, amarillo, "rojo");
        Pintor p3 = new Pintor(3, amarillo, cian, "verde");

        p1.start();
        p2.start();
        p3.start();
    }
}
