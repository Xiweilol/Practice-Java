public class Hello {
    public static void main(String[] args) {
        String cadena = "radar";

        String invertida = new StringBuilder(cadena).reverse().toString();

        System.out.println(invertida);
        if (cadena.equals(invertida)) {
            System.out.println("Nice");
        } else {
            System.out.println("No");
        }
    }
}