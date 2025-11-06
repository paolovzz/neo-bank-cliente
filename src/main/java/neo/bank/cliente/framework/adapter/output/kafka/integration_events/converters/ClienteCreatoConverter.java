package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.events.ClienteCreato;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IEClienteCreato;

@ApplicationScoped
public class ClienteCreatoConverter
        implements IntegrationEventConverter<ClienteCreato, IEClienteCreato>, IntegrationEventConverterMarker {

    @Override
    public IEClienteCreato convert(ClienteCreato domainEvent) {
        return new IEClienteCreato(domainEvent.getIdCliente().getId(), domainEvent.getUsernameCliente().getUsername(),
                domainEvent.getNomeCliente().getNome(), domainEvent.getCognomeCliente().getCognome(),
                domainEvent.getDataNascita().getData(), domainEvent.getCodiceFiscale().getCodice(), domainEvent.getEmail().getIndirizzo(),
                domainEvent.getTelefono().getNumero(), domainEvent.getResidenza().getResidenza());
    }

    @Override
    public Class<ClienteCreato> supportedDomainEvent() {
        return ClienteCreato.class;
    }

}
