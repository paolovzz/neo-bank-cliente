package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Iban;
import neo.bank.cliente.domain.models.vo.NumeroCarta;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

@Value
public class AssociaCartaCmd {
    
    private NumeroCarta numeroCarta;
    private Iban iban;
    private UsernameCliente usernameCliente;
}
