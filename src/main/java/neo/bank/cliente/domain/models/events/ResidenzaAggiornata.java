package neo.bank.cliente.domain.models.events;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Residenza;

@Value
public class ResidenzaAggiornata implements EventPayload {

    private Residenza residenza;
    @Override
    public String eventType() {
        return "ResidenzaAggiornata";
    }
}
