package neo.bank.cliente.domain.models.events;

import neo.bank.cliente.domain.models.vo.CodiceFiscale;
import neo.bank.cliente.domain.models.vo.CognomeCliente;
import neo.bank.cliente.domain.models.vo.DataNascita;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.NomeCliente;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

public record ClienteCreato(
        IdCliente idCliente,
        UsernameCliente usernameCliente,
        NomeCliente nomeCliente,
        CognomeCliente cognomeCliente,
        DataNascita dataNascita,
        CodiceFiscale codiceFiscale,
        Email email,
        Telefono telefono,
        Residenza residenza) implements EventPayload {

    @Override
    public String eventType() {
        return "ClienteCreato";
    }
}
