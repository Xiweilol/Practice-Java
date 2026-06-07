package gm.zona_fit;

import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger log = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
		log.info("Iniciando Spring Boot");
		//levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		log.info("Aplicacion Finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("***Aplicacion Zona Fit (Gym)***");
	}
}
