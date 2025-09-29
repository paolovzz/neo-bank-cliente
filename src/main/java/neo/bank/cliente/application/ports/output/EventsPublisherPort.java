package neo.bank.cliente.application.ports.output;

import java.util.List;

import neo.bank.cliente.domain.models.events.EventPayload;

public interface EventsPublisherPort {
    void publish(String aggregateName, String aggregateId, List<EventPayload> events);
    void publishError(String aggregateName, String eventName, Object body);
}
