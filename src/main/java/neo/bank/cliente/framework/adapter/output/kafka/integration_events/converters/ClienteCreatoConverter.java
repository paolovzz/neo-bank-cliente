package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.events.ClienteCreato;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IEClienteCreato;

@ApplicationScoped
public class ClienteCreatoConverter
        implements IntegrationEventConverter<ClienteCreato, IEClienteCreato>, IntegrationEventConverterMarker {

    @Override
    public IEClienteCreato convert(ClienteCreato domainEvent) {
        return new IEClienteCreato(domainEvent.idCliente().id(), domainEvent.usernameCliente().username(),
                domainEvent.nomeCliente().nome(), domainEvent.cognomeCliente().cognome(),
                domainEvent.dataNascita().data(), domainEvent.codiceFiscale().codice(), domainEvent.email().indirizzo(),
                domainEvent.telefono().numero(), domainEvent.residenza().residenza());
    }

    @Override
    public Class<ClienteCreato> supportedDomainEvent() {
        return ClienteCreato.class;
    }

}
