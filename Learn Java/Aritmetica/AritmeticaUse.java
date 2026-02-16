package Aritmetica;

public class AritmeticaUse {

    int num1;
    int num2;

    //constructor
    public AritmeticaUse(int num1,int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    //funcion sumar
    public void sumar(){
        System.out.println("La suma es: " + (num1 + num2));
    }

    public void restar(){
        System.out.println("La resta es: " +(num1 - num2));
    }
}
