package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.events.ResidenzaAggiornata;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IEResidenzaAggiornata;

@ApplicationScoped
public class ResidenzaAggiornataConverter
        implements IntegrationEventConverter<ResidenzaAggiornata, IEResidenzaAggiornata>, IntegrationEventConverterMarker {

    @Override
    public IEResidenzaAggiornata convert(ResidenzaAggiornata domainEvent) {
        return new IEResidenzaAggiornata(domainEvent.residenza().residenza());
    }

    @Override
    public Class<ResidenzaAggiornata> supportedDomainEvent() {
        return ResidenzaAggiornata.class;
    }

}
