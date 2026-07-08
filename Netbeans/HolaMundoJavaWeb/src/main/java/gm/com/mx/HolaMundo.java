package gm.com.mx;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author xiwei
 */
//@WebServlet("/HolaMundoServlet") //http:8080/HolaMundoJavaWeb/HolaMundoServlet
public class HolaMundo extends HttpServlet{
    
      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
          response.setContentType("text/html;charset=UTF-8");
          var salida = response.getWriter();
          salida.print("Hola mundo desde Servlets");
      }
}   
