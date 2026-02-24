import java.util.HashMap;
import java.util.Map;
public class Mapa {

    public static void main(String args[]){
        Map <String,String> persona = new HashMap<>();

        persona.put("nombre","Diego");

        persona.put("Apellido","Flores");

        persona.put("edad","31");
        //persona.put("edad","32");

        System.out.println("Valores del mapa: ");
        persona.entrySet().forEach(System.out::println);

        //Iterar sobre los elementos del mapa por separado

        persona.forEach((llave,valor) ->{
            System.out.println("Llave: " + llave + ", Valor: " + valor);
        });
    }
}
