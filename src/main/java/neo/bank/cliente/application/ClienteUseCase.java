package neo.bank.cliente.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.application.ports.input.dto.AggiornaEmailCmd;
import neo.bank.cliente.application.ports.input.dto.AggiornaResidenzaCmd;
import neo.bank.cliente.application.ports.input.dto.AggiornaTelefonoCmd;
import neo.bank.cliente.application.ports.input.dto.AssociaContoCorrenteCmd;
import neo.bank.cliente.application.ports.input.dto.CreaClienteCmd;
import neo.bank.cliente.application.ports.output.ClienteOutputPort;
import neo.bank.cliente.application.ports.output.UsernameProjectionRepositoryPort;
import neo.bank.cliente.domain.models.aggregates.Cliente;
import neo.bank.cliente.domain.models.vo.Iban;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.UsernameCliente;
import neo.bank.cliente.domain.services.GeneratoreIdClienteService;

@ApplicationScoped
@Slf4j
public class ClienteUseCase {
    
    @Inject
    private GeneratoreIdClienteService generatoreIdCliente;

    @Inject
    private ClienteOutputPort clienteOutputPort;

    @Inject
    private UsernameProjectionRepositoryPort usernamePort;

    public void creaCliente(CreaClienteCmd cmd) {
        log.info("Comando [creaCliente] in esecuzione...");
        Cliente cliente = Cliente.crea(generatoreIdCliente, cmd.getUsernameCliente(), cmd.getNomeCliente(), cmd.getCognomeCliente(), cmd.getDataNascita(), cmd.getCodiceFiscale(), cmd.getEmail(), cmd.getTelefono(), cmd.getResidenza());
        clienteOutputPort.salva(cliente);
        usernamePort.salva(cmd.getUsernameCliente(), cliente.getIdCliente());
        log.info("Comando [creaCliente] terminato...");
    }

     public void associaContoCorrente(AssociaContoCorrenteCmd cmd) {
        log.info("Comando [associaContoCorrente] in esecuzione...");
        IdCliente idCliente = usernamePort.recuperaDaUsername(cmd.getUsernameCliente());
        Cliente cliente = clienteOutputPort.recuperaDaId(idCliente);
        cliente.associaContoCorrente(cmd.getIban());
        clienteOutputPort.salva(cliente);
        log.info("Comando [associaContoCorrente] terminato...");
    }

    public Cliente recuperaClienteDaUsername(UsernameCliente usernameCliente) {
        log.info("Recupero info cliente per username [{}]", usernameCliente.username());
        IdCliente idCliente = usernamePort.recuperaDaUsername(usernameCliente);
        Cliente cliente = clienteOutputPort.recuperaDaId(idCliente);
        log.info("Recupero terminato");
        return cliente;
    }

    public List<Iban> recuperaCodiciIbanDelCliente(UsernameCliente usernameCliente) {
        log.info("Recupero iban cliente per username [{}]", usernameCliente.username());
        IdCliente idCliente = usernamePort.recuperaDaUsername(usernameCliente);
        Cliente cliente = clienteOutputPort.recuperaDaId(idCliente);
        log.info("Recupero terminato");
        return cliente.getContiAssociati();
    }

    public void aggiornaResidenza(AggiornaResidenzaCmd cmd) {
        log.info("Comando [aggiornaResidenza] in esecuzione...");
        IdCliente idCliente = usernamePort.recuperaDaUsername(cmd.getUsernameCliente());
        Cliente cliente = clienteOutputPort.recuperaDaId(idCliente);
        cliente.aggiornaResidenza(cmd.getResidenza());
        clienteOutputPort.salva(cliente);
        log.info("Comando [aggiornaResidenza] terminato...");
    }

    public void aggiornaTelefono(AggiornaTelefonoCmd cmd) {
        log.info("Comando [aggiornaTelefono] in esecuzione...");
        IdCliente idCliente = usernamePort.recuperaDaUsername(cmd.getUsernameCliente());
        Cliente cliente = clienteOutputPort.recuperaDaId(idCliente);
        cliente.aggiornaTelefono(cmd.getTelefono());
        clienteOutputPort.salva(cliente);
        log.info("Comando [aggiornaTelefono] terminato...");
    }


    public void aggiornaEmail(AggiornaEmailCmd cmd) {
        log.info("Comando [aggiornaEmail] in esecuzione...");
         IdCliente idCliente = usernamePort.recuperaDaUsername(cmd.getUsernameCliente());
        Cliente cliente = clienteOutputPort.recuperaDaId(idCliente);
        cliente.aggiornaEmail(cmd.getEmail());
        clienteOutputPort.salva(cliente);
        log.info("Comando [aggiornaEmail] terminato...");
    }

}