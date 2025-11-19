package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

@Value
public class RecuperaDatiClienteCmd {
    private UsernameCliente usernameCliente;
}
