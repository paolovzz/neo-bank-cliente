package neo.bank.cliente.domain.models.events;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

@Value
public class EmailAggiornata implements EventPayload {

    private Email email;
    private UsernameCliente usernameCliente;
    @Override
    public String eventType() {
        return "EmailAggiornata";
    }
}
