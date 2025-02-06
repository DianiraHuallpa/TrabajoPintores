package PintoresManejador;

public class ManejadorReservas {
    public synchronized void tomaPareja(Cubo d, Cubo d2) throws InterruptedException {
        while (d.isEnUso() || d2.isEnUso()) {
            wait();
        }
        d.setEnUso(true);
        d2.setEnUso(true);
    }

    public synchronized void liberarPareja(Cubo d, Cubo d2) {
        d.setEnUso(false);
        d2.setEnUso(false);
        notifyAll();
    }
}
