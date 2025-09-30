package neo.bank.cliente.application.ports.input.dto;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.CodiceFiscale;
import neo.bank.cliente.domain.models.vo.CognomeCliente;
import neo.bank.cliente.domain.models.vo.DataNascita;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.NomeCliente;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

@Value
public class CreaClienteCmd {
    
    private UsernameCliente usernameCliente;
    private NomeCliente nomeCliente;
    private CognomeCliente cognomeCliente;
    private DataNascita dataNascita;
    private CodiceFiscale codiceFiscale;
    private Email email;
    private Telefono telefono;
    private  Residenza residenza;
}
