package neo.bank.cliente.domain.models.events;

import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

public record EmailAggiornata(UsernameCliente usenameCliente, Email email) implements EventPayload {

    @Override
    public String eventType() {
        return "EmailAggiornata";
    }
}
