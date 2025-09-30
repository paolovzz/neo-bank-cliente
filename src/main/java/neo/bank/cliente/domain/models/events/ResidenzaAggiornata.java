package neo.bank.cliente.domain.models.events;

import neo.bank.cliente.domain.models.vo.Residenza;

public record ResidenzaAggiornata(Residenza residenza) implements EventPayload {

    @Override
    public String eventType() {
        return "ResidenzaAggiornata";
    }
}
