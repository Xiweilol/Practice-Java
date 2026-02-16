package persona;

public class Persona {

    private String name;
    private String apellido;

    public Persona(String name,String apellido){
        this.name = name;
        this.apellido  = apellido;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getApellido(){
        return this.apellido;
    }
}
