package neo.bank.cliente.framework.adapter.output.services;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.services.GeneratoreIdClienteService;

@ApplicationScoped
public class GeneratoreIdClienteServiceImpl implements GeneratoreIdClienteService {

    @Override
    public IdCliente genera() {
        return new IdCliente(UUID.randomUUID().toString());
    }
    
}
