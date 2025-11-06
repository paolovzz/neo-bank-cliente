package neo.bank.cliente.framework.adapter.output.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import neo.bank.cliente.application.exceptions.ClienteNonTrovatoException;
import neo.bank.cliente.application.ports.output.ClienteOutputPort;
import neo.bank.cliente.application.ports.output.ClienteRepositoryPort;
import neo.bank.cliente.application.ports.output.EventsPublisherPort;
import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.events.EventPayload;
import neo.bank.cliente.domain.models.vo.IdCliente;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ClienteOutputAdapter  implements ClienteOutputPort{

    private final ClienteRepositoryPort ccRepo;
    private final EventsPublisherPort publisherPort;

    @Override
    public void salva(Cliente cc) {

        List<EventPayload> events = cc.popChanges();
        ccRepo.save(cc.getIdCliente(), events);
        publisherPort.publish(Cliente.AGGREGATE_NAME, cc.getIdCliente().getId(), events);
    }

    @Override
    public Cliente recuperaDaId(IdCliente idCliente) {
        Cliente cliente = ccRepo.findById(idCliente.getId());
        if(cliente == null) {
            throw new ClienteNonTrovatoException(idCliente.getId());
        }
       return cliente;
    }
    
}
