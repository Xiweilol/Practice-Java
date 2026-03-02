package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class CustomerController {

    //Simular bases de datos
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123,"Juan Pabalo", "LOlito","contrasena123"),
            new Customer(323,"Alejandro gawd", "loquitos","POEWUS"),
            new Customer(456,"Carlos Carrillo", "xsaidan","Ppaowf"),
            new Customer(985,"Emilio NOyola", "eso eso","POEW''")
    ));
    @GetMapping("clientes")
    public List<Customer> getCustomers(){
        return customers;
    }


    //creaer otro end point que busca or su nombre de cliente
    @GetMapping("clientes/{name}")
    public Customer getCliente(@PathVariable String name){
        for(Customer c : customers){
            if(c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }
    


}
