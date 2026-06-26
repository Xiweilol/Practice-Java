package gm.zona_fit;

import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger log = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator();
	public static void main(String[] args) {
		log.info("Iniciando Spring Boot");
		//levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		log.info("Aplicacion Finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp(){

		var salir = false;
		var consola = new Scanner(System.in);

		while(!salir) {
			var opcion = mostrarMenu(consola);

			switch (opcion) {
				case 1:
					clienteServicio.listarCLiente().forEach(System.out::println);
					break;
				case 2:

			}
			//salir = ejecutarOpciones(consola, opcion);
			log.info("");
		}
	}

	private int mostrarMenu(Scanner consola){
		log.info("""
			***Aplicacion Zona Fit (Gym)***
			1. Mostra cliente
			2. Buscar cliente
			3. Agregar cliente
			4. Modificar cliente
			5. 
				""");
		int  opcion = consola.nextInt();
		return opcion;
	}
}
