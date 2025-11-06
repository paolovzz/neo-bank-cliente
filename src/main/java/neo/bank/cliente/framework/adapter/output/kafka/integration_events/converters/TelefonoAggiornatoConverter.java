package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.events.TelefonoAggiornato;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IETelefonoAggiornato;

@ApplicationScoped
public class TelefonoAggiornatoConverter implements IntegrationEventConverter<TelefonoAggiornato, IETelefonoAggiornato>, IntegrationEventConverterMarker {

    @Override
    public IETelefonoAggiornato convert(TelefonoAggiornato domainEvent) {
        return new IETelefonoAggiornato(domainEvent.getTelefono().getNumero());
    }

    @Override
    public Class<TelefonoAggiornato> supportedDomainEvent() {
        return TelefonoAggiornato.class;
    }

}
