package neo.bank.cliente.domain.models.events;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Email;

@Value
public class EmailAggiornata implements EventPayload {

    private Email email;
    @Override
    public String eventType() {
        return "EmailAggiornata";
    }
}
