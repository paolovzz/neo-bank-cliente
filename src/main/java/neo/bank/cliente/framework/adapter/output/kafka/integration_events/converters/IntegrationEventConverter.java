package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import neo.bank.cliente.domain.models.events.EventPayload;

public interface IntegrationEventConverter<DE extends EventPayload, IE> {
    IE convert(DE domainEvent);
}