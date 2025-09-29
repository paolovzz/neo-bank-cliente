package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import neo.bank.cliente.domain.models.events.EventPayload;

public interface IntegrationEventConverterMarker {
    Class<? extends EventPayload> supportedDomainEvent();
}
