package com.Trying.Practice1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerPalin {

    @GetMapping("/palindromo/{palabra}")
    public String impresion(@PathVariable String palabra){

        if(Palindrome(palabra)){
            return "La palabra " + palabra + " " + "SI es palindromo";
        } else {
            return "La palabra " + palabra + " " + "NO es palindromo";
        }
    }
    //metodo para verificar si una palabra es palindrome o no
    private boolean Palindrome(String palabra){
        //normalizamos la palabra
        palabra = palabra.toLowerCase();
        //utilizamos metodo reverse de Stringbuilder para invertir una palabra
        //ya que si una palabra es igual leyendo de derecha a izquiera, si lo invertimos es igual
        String invertida = new StringBuilder(palabra).reverse().toString();
        //retorna true si la palabra es igual que su invertida
        return (palabra.equals(invertida));
    }
}
