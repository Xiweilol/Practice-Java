package gm.zona_fit.repositorio;

import gm.zona_fit.modulo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//encarga de hacer las operaciones de sql
public interface ClienteRepositorio extends JpaRepository<Cliente,Integer> {

}
