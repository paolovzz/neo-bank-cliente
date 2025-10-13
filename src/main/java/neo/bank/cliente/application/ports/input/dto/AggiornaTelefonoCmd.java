package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

@Value
public class AggiornaTelefonoCmd {
    
    private UsernameCliente usernameCliente;
    private Telefono telefono;
}
