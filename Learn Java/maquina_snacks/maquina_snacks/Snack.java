package maquina_snacks;

import java.io.Serializable;
//estandar de java bean
public class Snack implements Serializable {
    private static int contador;
    private int idSnack;
    private String nombre;
    private double precio;

    public Snack(){
        this.idSnack = Snack.contador++;
    }
}
