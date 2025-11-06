package neo.bank.cliente.domain.models.events;

import lombok.Value;
import neo.bank.cliente.domain.models.vo.Telefono;

@Value
public class TelefonoAggiornato implements EventPayload {

    private Telefono telefono;

    @Override
    public String eventType() {
        return "TelefonoAggiornato";
    }
}
