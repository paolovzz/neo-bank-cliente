package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

@Value
public class AggiornaEmailCmd {
    
    private UsernameCliente usernameCliente;
    private Email email;
}
