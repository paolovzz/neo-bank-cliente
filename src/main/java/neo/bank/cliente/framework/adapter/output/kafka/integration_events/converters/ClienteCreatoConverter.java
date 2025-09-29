package neo.bank.cliente.framework.adapter.output.kafka.integration_events.converters;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.events.ClienteCreato;
import neo.bank.cliente.domain.models.vo.DatiAnagraficiCliente;
import neo.bank.cliente.framework.adapter.output.kafka.integration_events.IEClienteCreato;

@ApplicationScoped
public class ClienteCreatoConverter implements IntegrationEventConverter<ClienteCreato, IEClienteCreato>, IntegrationEventConverterMarker{

    @Override
    public IEClienteCreato convert(ClienteCreato domainEvent) {
        DatiAnagraficiCliente datiAnagraficiCliente = domainEvent.datiAnagrafici();
        return new IEClienteCreato(domainEvent.idCliente().id(), datiAnagraficiCliente.usernameCliente().username(), datiAnagraficiCliente.nomeCliente().nome(), datiAnagraficiCliente.cognomeCliente().cognome(),datiAnagraficiCliente.dataNascita().data(), datiAnagraficiCliente.codiceFiscale().codice(), datiAnagraficiCliente.email().indirizzo(), datiAnagraficiCliente.telefono().numero(), datiAnagraficiCliente.residenza().residenza());
    }

    @Override
    public Class<ClienteCreato> supportedDomainEvent() {
        return ClienteCreato.class;
    }
    
}
