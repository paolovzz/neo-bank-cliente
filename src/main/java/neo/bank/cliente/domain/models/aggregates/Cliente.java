package neo.bank.cliente.domain.models.aggregates;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.cliente.domain.models.events.CartaAssociata;
import neo.bank.cliente.domain.models.events.ClienteCreato;
import neo.bank.cliente.domain.models.events.ContoCorrenteAssociato;
import neo.bank.cliente.domain.models.events.EmailAggiornata;
import neo.bank.cliente.domain.models.events.EventPayload;
import neo.bank.cliente.domain.models.events.ResidenzaAggiornata;
import neo.bank.cliente.domain.models.events.TelefonoAggiornato;
import neo.bank.cliente.domain.models.exceptions.BusinessRuleException;
import neo.bank.cliente.domain.models.vo.CodiceFiscale;
import neo.bank.cliente.domain.models.vo.CognomeCliente;
import neo.bank.cliente.domain.models.vo.DataNascita;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.Iban;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.NomeCliente;
import neo.bank.cliente.domain.models.vo.NumeroCarta;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;
import neo.bank.cliente.domain.services.GeneratoreIdClienteService;


@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends AggregateRoot<Cliente> implements Applier  {

    public static final String AGGREGATE_NAME = "CLIENTE";
    private IdCliente idCliente;
    private UsernameCliente usernameCliente;
    private NomeCliente nomeCliente;
    private CognomeCliente cognomeCliente;
    private DataNascita dataNascita;
    private CodiceFiscale codiceFiscale;
    private Email email;
    private Telefono telefono;
    private Residenza residenza;
    private List<Iban> contiAssociati = new ArrayList<>();
    private List<NumeroCarta> carteAssociate = new ArrayList<>();

    public static Cliente crea(GeneratoreIdClienteService generatoreIdCliente, UsernameCliente usernameCliente,
        NomeCliente nomeCliente,
        CognomeCliente cognomeCliente,
        DataNascita dataNascita,
        CodiceFiscale codiceFiscale,
        Email email,
        Telefono telefono,
        Residenza residenza) {

        IdCliente idCliente = generatoreIdCliente.genera();
        Cliente cliente = new Cliente();
        cliente.idCliente = idCliente;
        cliente.events(new ClienteCreato(idCliente, usernameCliente, nomeCliente, cognomeCliente, dataNascita, codiceFiscale, email, telefono, residenza));
        return cliente;
    }

    public void aggiornaResidenza(Residenza residenza) {
        events(new ResidenzaAggiornata(residenza));
    }

    public void associaCarta(NumeroCarta numeroCarta, Iban iban) {
        if(!contiAssociati.contains(iban)) {
            throw new BusinessRuleException("Iban non associata al cliente");
        }
        events(new CartaAssociata(numeroCarta));
    }

    public void aggiornaTelefono(Telefono telefono) {
        events(new TelefonoAggiornato(telefono));
    }

    public void aggiornaEmail(Email email) {
        events(new EmailAggiornata(usernameCliente, email));
    }

    public void associaContoCorrente(Iban iban) {
        if(contiAssociati.stream().anyMatch(e -> e.equals(iban))) {
            throw new BusinessRuleException("Conto corrente gia associato");
        }

        events(new ContoCorrenteAssociato(iban));
    }

    private void apply(ClienteCreato event) {
        this.idCliente = event.idCliente();
        this.nomeCliente = event.nomeCliente();
        this.usernameCliente = event.usernameCliente();
        this.nomeCliente = event.nomeCliente();
        this.cognomeCliente = event.cognomeCliente();
        this.dataNascita = event.dataNascita();
        this.codiceFiscale = event.codiceFiscale();
        this.email = event.email();
        this.telefono = event.telefono();
        this.residenza = event.residenza();

    }

    private void apply(ResidenzaAggiornata event) {
        this.residenza = event.residenza();
    }

    private void apply(CartaAssociata event) {
        this.carteAssociate.add(event.numeroCarta());
    }

    private void apply(ContoCorrenteAssociato event) {
        this.contiAssociati.add(event.iban());
    }

    private void apply(TelefonoAggiornato event) {
        this.telefono = event.telefono();
    }

    private void apply(EmailAggiornata event) {
        this.email = event.email();
    }

    @Override
    public void apply(EventPayload event) {
         switch (event) {
            case ClienteCreato ev -> apply((ClienteCreato) ev);
            case ResidenzaAggiornata ev -> apply((ResidenzaAggiornata) ev);
            case TelefonoAggiornato ev -> apply((TelefonoAggiornato) ev);
            case EmailAggiornata ev -> apply((EmailAggiornata) ev);
            case ContoCorrenteAssociato ev -> apply((ContoCorrenteAssociato) ev);
            case CartaAssociata ev -> apply((CartaAssociata) ev);
            default -> throw new IllegalArgumentException("Evento non supportato");
        }
    }
}