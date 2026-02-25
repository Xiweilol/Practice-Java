package maquina_snacks;

import java.io.Serializable;
import java.util.Objects;

/*Clase de dominio*/
public class Snack implements Serializable {
    //variable estatico que se puede utilizar en cualqueier parte del codigo
    private static int contador = 0;

    private int idSnack;

    private String nombre;

    private double precio;
    //constructor java beans
    public Snack(){
        this.idSnack = ++Snack.contador;
    }

    //constructor
    public Snack(String nombre, double precio){
        //automaticamente mandar a llamar constructor vacio
        this();
        this.nombre = nombre;
        this.precio = precio;
        //quien sabe para que pero dice que si

    }

    public int getIdSnack(){
        return this.idSnack;
    }

    public void setIdSnack(int idSnack) {
        this.idSnack = idSnack;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public static int getContador() {
        return contador;
    }
    //tipo sobre carga de clases??
    @Override
    public String toString() {
        return "Snack{" +
                "idSnack=" + idSnack +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}
