package neo.bank.cliente.domain.models.events;

import neo.bank.cliente.domain.models.vo.NumeroCarta;

public record CartaAssociata( NumeroCarta numeroCarta) implements EventPayload {

    @Override
    public String eventType() {
        return "CartaAssociata";
    }
}
