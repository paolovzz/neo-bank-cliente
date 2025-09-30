package neo.bank.cliente.domain.models.events;

import neo.bank.cliente.domain.models.vo.Telefono;

public record TelefonoAggiornato(Telefono telefono) implements EventPayload {

    @Override
    public String eventType() {
        return "TelefonoAggiornato";
    }
}
