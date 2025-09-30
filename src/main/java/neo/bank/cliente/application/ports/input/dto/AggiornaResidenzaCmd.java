package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.Residenza;

@Value
public class AggiornaResidenzaCmd {
    
    private IdCliente idCliente;
    private Residenza residenza;
}
