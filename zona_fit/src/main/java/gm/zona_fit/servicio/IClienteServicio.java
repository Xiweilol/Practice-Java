package gm.zona_fit.servicio;

import gm.zona_fit.modulo.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> listarCLiente();

    public Cliente buscarClientePorId(Integer idCliente);

    public void guardarCliente(Cliente cliente);

    public void EliminarCliente(Cliente cliente);

}
