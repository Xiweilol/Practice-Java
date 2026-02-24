import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Listas {
    public static void main(String [] args){
        //crear una variable de arraylist
        //sin <> es general, acepta cualqueir tipo de objetp
        //List miLista = new ArrayList();

        //especificando que es de tipo String
        List <String> miLista = new ArrayList<>();
        //metodo de añadir, cualquier tipo de datos
        miLista.add("Lunes");
        miLista.add("Martes");
        miLista.add("Miercoles");
        miLista.add("Jueves");
        miLista.add("Viernes");
        miLista.add("Sabado");
        miLista.add("Domingo");

        for(String element : miLista){
            System.out.println("Dia de la semana: " + element);
        }

        //Funciones lambda(funcion anonima de un codigo muy compacta)
        miLista.forEach(elemento -> {
            System.out.println("Elemento: " + elemento);
        });


        //funcion de metodos de referencia
        miLista.forEach(System.out::println);


        List<String> nombres = Arrays.asList("Pedro","Ivan", "Alex");

        nombres.forEach( elemento -> {
            System.out.println("Elementos en nombres : " + elemento);
        });

    }
}
