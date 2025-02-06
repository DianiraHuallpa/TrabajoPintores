package PintoresV2;
//NUMERO DE VECES QUE SE HA PRODUCIDO CADA MEZCLA DE PINTURAS

public class Resultado {
    private int azul;
    private int rojo;
    private int verde;
// 3 CONTADORES uno para cada color
    public Resultado () {
        this.azul = 0;
        this.rojo = 0;
        this.verde = 0;
    }
//actualización de resultados
    public void add (String color) {
        switch (color) {
            case "azul": {
                this.azul++;
                break;
            }
            case "rojo": {
                this.rojo++;
                break;
            }
            case "verde": {
                this.verde++;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Resultado {" +
                "azul=" + azul +
                ", rojo=" + rojo +
                ", verde=" + verde +
                '}';
    }
}
