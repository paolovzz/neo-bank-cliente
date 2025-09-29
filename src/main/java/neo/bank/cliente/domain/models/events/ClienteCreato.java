package neo.bank.cliente.domain.models.events;

import neo.bank.cliente.domain.models.vo.DatiAnagraficiCliente;
import neo.bank.cliente.domain.models.vo.IdCliente;

public record ClienteCreato(
        IdCliente idCliente,
        DatiAnagraficiCliente datiAnagrafici) implements EventPayload {

    @Override
    public String eventType() {
        return "ClienteCreato";
    }
}
