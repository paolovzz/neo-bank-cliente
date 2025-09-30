package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.Telefono;

@Value
public class AggiornaTelefonoCmd {
    
    private IdCliente idCliente;
    private Telefono telefono;
}
