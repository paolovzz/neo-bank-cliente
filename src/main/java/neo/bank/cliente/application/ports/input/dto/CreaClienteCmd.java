package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.DatiAnagraficiCliente;

@Value
public class CreaClienteCmd {
    
    private DatiAnagraficiCliente datiAnagrafici;
}
