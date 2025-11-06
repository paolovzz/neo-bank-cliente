package neo.bank.cliente.domain.models.events;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.CodiceFiscale;
import neo.bank.cliente.domain.models.vo.CognomeCliente;
import neo.bank.cliente.domain.models.vo.DataNascita;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.NomeCliente;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

@Value
public class ClienteCreato implements EventPayload {

    private IdCliente idCliente;
    private UsernameCliente usernameCliente;
    private NomeCliente nomeCliente;
    private CognomeCliente cognomeCliente;
    private DataNascita dataNascita;
    private CodiceFiscale codiceFiscale;
    private Email email;
    private Telefono telefono;
    private Residenza residenza;

    @Override
    public String eventType() {
        return "ClienteCreato";
    }
}
