package persona;

import persona.Persona;

public class pruebaPersona {

    public static void main(String [] args){
        var p1 = new Persona("Xivei","Huang");

        System.out.println("Nombre del tilin: " + p1.getName());
        System.out.println("Apellido del tilin: " + p1.getApellido());

        p1.setName("HAHA");
        p1.setApellido("LOL");
        System.out.println("Despues de modificar con setter nombnre: " + p1.getName());
        System.out.println("Despues de modificar con setter Apellido: " + p1.getApellido());
    }

}
