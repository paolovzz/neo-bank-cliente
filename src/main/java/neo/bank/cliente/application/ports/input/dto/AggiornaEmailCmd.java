package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.IdCliente;

@Value
public class AggiornaEmailCmd {
    
    private IdCliente idCliente;
    private Email email;
}
