import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        var console = new Scanner(System.in);

        String name = "";
        String Ingredient = "";
        int time = 0;
        String dificult = "";

        System.out.println("*** Recetas de Cocina ***\n");
        System.out.print("Ingresa el nombre: ");

        name = console.nextLine();

        System.out.println("Ingresa los ingredientes: ");
        Ingredient = console.nextLine();
        System.out.println("Ingresa el tiempo de preparacion(min): ");
        time = Integer.parseInt(console.nextLine());
        System.out.println("Ingresa la dificultad: ");
        dificult = console.nextLine();

        System.out.println("--- Receta de Cocina ---");
        System.out.println("Nombre receta: " + name);

        System.out.println("Ingredientes: " + Ingredient);
        System.out.println("Tiempo de preparación: " + time);
        System.out.println("Dificultad: " + dificult);
    }
}
