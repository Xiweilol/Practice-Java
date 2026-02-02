import java.sql.SQLOutput;

public class StringMethods {

    public static void main(String [] args){
        System.out.println("*** Generador de Email ***");

        String name = "   Ubaldo Acosta Soto  ";

        System.out.println("Nombre usuario: " + name);
        name = name.toLowerCase().strip();

        String newOne = name.replace(' ','.');

        System.out.println("Nombre usuario normalizado: " + newOne);

        System.out.println("\n");

        String nameCompany = "     Global Mentoring  ";

        System.out.println("Nombre empresa: " + nameCompany);

        String extention = ".com.mx";
        System.out.println("Extensión del dominio: " + extention);
        nameCompany = nameCompany.toLowerCase().trim().replace(" ","");
        String norma = "@"+nameCompany+extention;

        System.out.println("Dominio de email normalizado: "+norma);

        String result = newOne+norma;
        System.out.print("\nEmail final generado: " + result);


    }

}
