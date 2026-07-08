package gm.Inventario;

import gm.Inventario.modulo.Producto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventarioApplication.class, args);

		//pRUEBA DE LOMBOK: Crear un objeto Producto y usar toString()
		Producto producto = new Producto();
		producto.setIdProducto(1);
		producto.setDescripcion("Pantalon");
		producto.setPrecio(300.0);
		producto.setExistencia(100);

		//IMprimir el objeto usando tostring de lombok
		System.out.println(producto);

		//Probar los getter y setter generado por Lombok
		producto.setDescripcion("LaptopGamer");
		producto.setPrecio(2000.00);
		System.out.println("Descripción nueva: "+ producto.getDescripcion());
		System.out.println("Precio nuevo: " + producto.getPrecio());

	}

}
