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

    static class Task {
        int id;
        String description;
        Status status;
        String createdAt;
        String updatedAt;

        Task(int id,String description, Status sstatus, String)

    }
}