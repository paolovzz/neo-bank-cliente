package neo.bank.cliente.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.application.ports.input.dto.CreaClienteCmd;
import neo.bank.cliente.application.ports.output.ClienteOutputPort;
import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.services.GeneratoreIdClienteService;

@ApplicationScoped
@Slf4j
public class ClienteUseCase {
    
    @Inject
    private GeneratoreIdClienteService generatoreIdCliente;

    @Inject
    private ClienteOutputPort clienteOutputPort;

    public void creaCliente(CreaClienteCmd cmd) {
        log.info("Comando [creaCliente] in esecuzione...");
        Cliente cliente = Cliente.crea(generatoreIdCliente, cmd.getDatiAnagrafici());
        clienteOutputPort.salva(cliente);
        log.info("Comando [creaCliente] terminato...");
    }

}