package cursoSpringBoot.domain;

// Clase POJO
public class Customer {
    //Crear atribustos
    private int ID;
    private String name;
    private String userName;
    private String passWord;

    //constructor


    public Customer(int ID, String name, String userName, String passWord) {
        this.ID = ID;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
