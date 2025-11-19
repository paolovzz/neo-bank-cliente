package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.events.EmailAggiornata;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IEEmailAggiornata;

@ApplicationScoped
public class EmailAggiornataConverter implements IntegrationEventConverter<EmailAggiornata, IEEmailAggiornata>, IntegrationEventConverterMarker {

    @Override
    public IEEmailAggiornata convert(EmailAggiornata domainEvent) {
        return new IEEmailAggiornata( domainEvent.getEmail().getIndirizzo(), domainEvent.getUsernameCliente().getUsername());
    }

    @Override
    public Class<EmailAggiornata> supportedDomainEvent() {
        return EmailAggiornata.class;
    }

}
