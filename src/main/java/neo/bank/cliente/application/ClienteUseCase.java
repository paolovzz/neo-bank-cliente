package neo.bank.cliente.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.application.ports.input.dto.AggiornaResidenzaCmd;
import neo.bank.cliente.application.ports.input.dto.AggiornaTelefonoCmd;
import neo.bank.cliente.application.ports.input.dto.CreaClienteCmd;
import neo.bank.cliente.application.ports.output.ClienteOutputPort;
import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.vo.IdCliente;
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
        Cliente cliente = Cliente.crea(generatoreIdCliente, cmd.getUsernameCliente(), cmd.getNomeCliente(), cmd.getCognomeCliente(), cmd.getDataNascita(), cmd.getCodiceFiscale(), cmd.getEmail(), cmd.getTelefono(), cmd.getResidenza());
        clienteOutputPort.salva(cliente);
        log.info("Comando [creaCliente] terminato...");
    }

    public Cliente recuperaClienteDaId(IdCliente idCliente) {
        log.info("Recupero info cliente per id [{}]", idCliente.id());
        Cliente cliente = clienteOutputPort.recuperaDaId(idCliente);
        log.info("Recupero terminato");
        return cliente;
    }

    public void aggiornaResidenza(AggiornaResidenzaCmd cmd) {
        log.info("Comando [aggiornaResidenza] in esecuzione...");
        Cliente cliente = clienteOutputPort.recuperaDaId(cmd.getIdCliente());
        cliente.aggiornaResidenza(cmd.getResidenza());
        clienteOutputPort.salva(cliente);
        log.info("Comando [aggiornaResidenza] terminato...");
    }

    public void aggiornaTelefono(AggiornaTelefonoCmd cmd) {
        log.info("Comando [aggiornaTelefono] in esecuzione...");
        Cliente cliente = clienteOutputPort.recuperaDaId(cmd.getIdCliente());
        cliente.aggiornaTelefono(cmd.getTelefono());
        clienteOutputPort.salva(cliente);
        log.info("Comando [aggiornaTelefono] terminato...");
    }

}