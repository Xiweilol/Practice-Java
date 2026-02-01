import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskTracker{
    private static final path DB_PATH = Paths.get("tasks.json");

    //enum sirve para enlistar conjunto de estados cerrado, o definidos ya
    //que ya no cambia siempre esta asi
    enum Status{
        //representa 3 estados posibles de una tarea
        TODO("todo"),
        IN_PROGRESS("in_progress"),
        DONE("done");

        //utilizamos final, para que una vez reciba el para metro, ya no se modifique
        final String value;
        //constructor para modificar el valor de estado
        Status(String value){this.value = value;}

        // convertidor, recibe lo que escribe el usuario
        //lo convierte en un estatus que definimos arriba
        static Status from(String s){
            if(s == null) return null;
            s = s.trim().toLowerCase(Locale.ROOT);
            return switch (s){
                case "todo" -> TODO;
                case "in-progress","inprogress","progress" -> IN_PROGRESS;
                case "todo" -> DONE;
                default -> null;
            };
        }
    }


    //una clase statico, no necesitamos instanciar su clase padre para existir, lo podemos instanciar directo al task
    //sirve para agrupar las informaciones necesarias al momento de crear una tarea
    static class Task {
        int id;
        String description;
        Status status;
        String createdAt;
        String updatedAt;

        Task(int id,String description, Status status, String createdAt, String updatedAt){
            this.id = id;
            this.description = description;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }

    /*//encarga de, crear una nueva tarea con
    - un nuevo id
    - la descripcion que da el usuario
    - estado inicial TODO
    - fechas createdAt y updatedAt
    - Agregarlo al json que habiamos creado
    */
    private static void addTask(List <Task> tasks, String desc){
        //utiliza el flujo de stream para buscar en la lista de tipo task el id
        //buscamos el maximo si no existe automaticamente crea uno que empieza desde 1
        int nextId = tasks.stream().mapToInt(t -> t.id).max().orElse(0) + 1;
        /*Alternativa de obtener el nuevo id
        * int nextId = 1;
        * for(Task t : tasks){
        *   if(t.id >= nextId){
        *       nextId = t.id + 1;
        *   }
        * }
        * */
        //Obtiene la fecha actual del sistema
        String now = LocalDateTime.now().toString();
        // aqui se crea objeto tarea
        //con id nuevo, la descripcion, el status que empieza con todo, now para el tiempo de creacion
        // el otro now era para su fecha de ultima modificacion
        Task t = new Task(nextId,desc,Status.TODO,now,now);
        // lo añade a la list del json
        tasks.add(t);
        // imprime si fue exitosa
        System.out.println("Tarea agregada con id " + nextId);

    }
}