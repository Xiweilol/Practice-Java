import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple Task Tracker CLI (no external libraries).
 * Stores tasks in ./tasks.json
 */
public class TaskTracker {

    private static final Path DB_PATH = Paths.get("tasks.json");

    enum Status {
        TODO("todo"),
        IN_PROGRESS("in-progress"),
        DONE("done");

        final String value;
        Status(String value) { this.value = value; }

        static Status from(String s) {
            if (s == null) return null;
            s = s.trim().toLowerCase(Locale.ROOT);
            return switch (s) {
                case "todo" -> TODO;
                case "in-progress", "inprogress", "progress" -> IN_PROGRESS;
                case "done" -> DONE;
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

        Task(int id, String description, Status status, String createdAt, String updatedAt) {
            this.id = id;
            this.description = description;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }

    public static void main(String[] args) {
        try {
            ensureDbExists();

            if (args.length == 0) {
                printHelp();
                return;
            }

            String command = args[0].toLowerCase(Locale.ROOT);
            List<Task> tasks = loadTasks();

            switch (command) {
                case "add" -> {
                    if (args.length < 2) {
                        System.out.println("Error: falta la descripción.\nUso: add \"descripcion\"");
                        return;
                    }
                    String desc = joinFrom(args, 1);
                    addTask(tasks, desc);
                    saveTasks(tasks);
                }
                case "update" -> {
                    if (args.length < 3) {
                        System.out.println("Error: faltan argumentos.\nUso: update <id> \"nueva descripcion\"");
                        return;
                    }
                    int id = parseId(args[1]);
                    if (id == -1) return;

                    String newDesc = joinFrom(args, 2);
                    if (!updateTask(tasks, id, newDesc)) {
                        System.out.println("Error: no existe una tarea con id " + id);
                        return;
                    }
                    saveTasks(tasks);
                }
                case "delete" -> {
                    if (args.length < 2) {
                        System.out.println("Error: falta el id.\nUso: delete <id>");
                        return;
                    }
                    int id = parseId(args[1]);
                    if (id == -1) return;

                    if (!deleteTask(tasks, id)) {
                        System.out.println("Error: no existe una tarea con id " + id);
                        return;
                    }
                    saveTasks(tasks);
                }
                case "mark" -> {
                    if (args.length < 3) {
                        System.out.println("Error: faltan argumentos.\nUso: mark <id> <todo|in-progress|done>");
                        return;
                    }
                    int id = parseId(args[1]);
                    if (id == -1) return;

                    Status st = Status.from(args[2]);
                    if (st == null) {
                        System.out.println("Error: estado inválido. Usa: todo | in-progress | done");
                        return;
                    }
                    if (!markTask(tasks, id, st)) {
                        System.out.println("Error: no existe una tarea con id " + id);
                        return;
                    }
                    saveTasks(tasks);
                }
                case "list" -> {
                    // list [status]
                    if (args.length == 1) {
                        printTasks(tasks, null);
                    } else {
                        Status st = Status.from(args[1]);
                        if (st == null) {
                            System.out.println("Error: filtro inválido. Usa: todo | in-progress | done");
                            return;
                        }
                        printTasks(tasks, st);
                    }
                }
                case "help", "--help", "-h" -> printHelp();
                default -> {
                    System.out.println("Comando desconocido: " + command);
                    printHelp();
                }
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    // ---------- Commands ----------
    private static void addTask(List<Task> tasks, String desc) {
        int nextId = tasks.stream().mapToInt(t -> t.id).max().orElse(0) + 1;
        String now = LocalDateTime.now().toString();
        Task t = new Task(nextId, desc, Status.TODO, now, now);
        tasks.add(t);
        System.out.println("Tarea agregada con id " + nextId);
    }

    private static boolean updateTask(List<Task> tasks, int id, String newDesc) {
        Task t = findById(tasks, id);
        if (t == null) return false;
        t.description = newDesc;
        t.updatedAt = LocalDateTime.now().toString();
        System.out.println("Tarea " + id + " actualizada.");
        return true;
    }

    private static boolean deleteTask(List<Task> tasks, int id) {
        boolean removed = tasks.removeIf(t -> t.id == id);
        if (removed) System.out.println("Tarea " + id + " eliminada.");
        return removed;
    }

    private static boolean markTask(List<Task> tasks, int id, Status st) {
        Task t = findById(tasks, id);
        if (t == null) return false;
        t.status = st;
        t.updatedAt = LocalDateTime.now().toString();
        System.out.println("Tarea " + id + " marcada como " + st.value + ".");
        return true;
    }

    // ---------- Printing ----------
    private static void printTasks(List<Task> tasks, Status filter) {
        List<Task> view = tasks;
        if (filter != null) {
            view = tasks.stream().filter(t -> t.status == filter).toList();
        }

        if (view.isEmpty()) {
            System.out.println("(No hay tareas" + (filter != null ? " con estado " + filter.value : "") + ")");
            return;
        }

        // orden por id
        view = new ArrayList<>(view);
        view.sort(Comparator.comparingInt(t -> t.id));

        for (Task t : view) {
            System.out.println(
                    "#" + t.id +
                            " [" + t.status.value + "] " +
                            t.description +
                            " (created: " + t.createdAt + ", updated: " + t.updatedAt + ")"
            );
        }
    }

    // ---------- Storage ----------
    private static void ensureDbExists() throws IOException {
        if (!Files.exists(DB_PATH)) {
            Files.writeString(DB_PATH, "{\"tasks\":[]}\n", StandardOpenOption.CREATE);
        }
    }

    private static List<Task> loadTasks() throws IOException {
        String json = Files.readString(DB_PATH);

        // Parser simple: extrae objetos tipo:
        // {"id":1,"description":"...","status":"todo","createdAt":"...","updatedAt":"..."}
        List<Task> tasks = new ArrayList<>();

        Pattern objPattern = Pattern.compile("\\{\\s*\"id\"\\s*:\\s*(\\d+)\\s*,\\s*\"description\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"status\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"createdAt\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"updatedAt\"\\s*:\\s*\"(.*?)\"\\s*\\}",
                Pattern.DOTALL);
        Matcher m = objPattern.matcher(json);

        while (m.find()) {
            int id = Integer.parseInt(m.group(1));
            String desc = unescapeJson(m.group(2));
            Status st = Status.from(m.group(3));
            String createdAt = unescapeJson(m.group(4));
            String updatedAt = unescapeJson(m.group(5));

            if (st == null) st = Status.TODO; // fallback
            tasks.add(new Task(id, desc, st, createdAt, updatedAt));
        }

        return tasks;
    }

    private static void saveTasks(List<Task> tasks) throws IOException {
        // Generar JSON consistente (escapando strings)
        tasks.sort(Comparator.comparingInt(t -> t.id));

        StringBuilder sb = new StringBuilder();
        sb.append("{\"tasks\":[\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            sb.append("  {")
                    .append("\"id\":").append(t.id).append(",")
                    .append("\"description\":\"").append(escapeJson(t.description)).append("\",")
                    .append("\"status\":\"").append(t.status.value).append("\",")
                    .append("\"createdAt\":\"").append(escapeJson(t.createdAt)).append("\",")
                    .append("\"updatedAt\":\"").append(escapeJson(t.updatedAt)).append("\"")
                    .append("}");
            if (i < tasks.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]}\n");

        Files.writeString(DB_PATH, sb.toString(),
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

        System.out.println("Guardado en " + DB_PATH.toAbsolutePath());
    }

    // ---------- Helpers ----------
    private static Task findById(List<Task> tasks, int id) {
        for (Task t : tasks) if (t.id == id) return t;
        return null;
    }

    private static int parseId(String s) {
        try {
            int id = Integer.parseInt(s);
            if (id <= 0) {
                System.out.println("Error: el id debe ser > 0.");
                return -1;
            }
            return id;
        } catch (NumberFormatException e) {
            System.out.println("Error: id inválido: " + s);
            return -1;
        }
    }

    private static String joinFrom(String[] args, int start) {
        // Permite escribir sin comillas si quieres: add Hacer tarea de mate
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            if (i > start) sb.append(' ');
            sb.append(args[i]);
        }
        return sb.toString().trim();
    }

    private static String escapeJson(String s) {
        return s
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    private static String unescapeJson(String s) {
        return s
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .replace("\\\"", "\"")
                .replace("\\\\", "\\");
    }

    private static void printHelp() {
        System.out.println("""
TaskTracker (CLI)
Uso:
  add "descripcion"
  update <id> "nueva descripcion"
  delete <id>
  mark <id> <todo|in-progress|done>
  list [todo|in-progress|done]

Ejemplos:
  java TaskTracker add "Estudiar Java"
  java TaskTracker mark 1 in-progress
  java TaskTracker list done
""");
    }
}
