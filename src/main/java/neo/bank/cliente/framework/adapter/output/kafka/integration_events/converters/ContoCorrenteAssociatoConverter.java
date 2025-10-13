package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.events.ContoCorrenteAssociato;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IEContoCorrenteAssociato;

@ApplicationScoped
public class ContoCorrenteAssociatoConverter implements IntegrationEventConverter<ContoCorrenteAssociato, IEContoCorrenteAssociato>, IntegrationEventConverterMarker {

    @Override
    public IEContoCorrenteAssociato convert(ContoCorrenteAssociato domainEvent) {
        return new IEContoCorrenteAssociato(domainEvent.iban().codice());
    }

    @Override
    public Class<ContoCorrenteAssociato> supportedDomainEvent() {
        return ContoCorrenteAssociato.class;
    }

}
