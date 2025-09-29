package neo.bank.cliente.application.ports.output;

import java.util.List;

import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.events.EventPayload;
import neo.bank.cliente.domain.models.vo.IdCliente;

public interface ClienteRepositoryPort {
    
    public void save(IdCliente idCliente, List<EventPayload> events);
    public Cliente findById (String aggregateId);
}
