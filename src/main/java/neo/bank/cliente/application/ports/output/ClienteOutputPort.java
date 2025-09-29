package neo.bank.cliente.application.ports.output;

import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.vo.IdCliente;

public interface ClienteOutputPort {
    
    public void salva(Cliente cliente);
    public Cliente recuperaDaId(IdCliente idCliente);
}
