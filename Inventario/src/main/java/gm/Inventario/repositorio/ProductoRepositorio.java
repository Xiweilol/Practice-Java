package gm.Inventario.repositorio;

import gm.Inventario.modulo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto,Integer> {

}
