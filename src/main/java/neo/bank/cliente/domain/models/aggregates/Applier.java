package neo.bank.cliente.domain.models.aggregates;

import neo.bank.cliente.domain.models.events.EventPayload;

public interface Applier {
    void apply(EventPayload event);
}