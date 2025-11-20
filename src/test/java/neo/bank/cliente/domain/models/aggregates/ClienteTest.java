package neo.bank.cliente.domain.models.aggregates;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import neo.bank.cliente.domain.models.events.ClienteCreato;
import neo.bank.cliente.domain.models.vo.CodiceFiscale;
import neo.bank.cliente.domain.models.vo.CognomeCliente;
import neo.bank.cliente.domain.models.vo.DataNascita;
import neo.bank.cliente.domain.models.vo.Email;
import neo.bank.cliente.domain.models.vo.IdCliente;
import neo.bank.cliente.domain.models.vo.NomeCliente;
import neo.bank.cliente.domain.models.vo.Residenza;
import neo.bank.cliente.domain.models.vo.Telefono;
import neo.bank.cliente.domain.models.vo.UsernameCliente;

public class ClienteTest {

    private IdCliente idCliente = new IdCliente(UUID.randomUUID().toString());
    private UsernameCliente usernameCliente = new UsernameCliente("user");
    private NomeCliente nomeCliente = new NomeCliente("Mario");
    private CognomeCliente cognomeCliente = new CognomeCliente("Rossi");
    private DataNascita dataNascita = new DataNascita(LocalDate.of(1991, 9, 20));
    private CodiceFiscale codiceFiscale = new CodiceFiscale("RSSMRA91P20H501U");
    private Email email = new Email("test@mail.it");
    private Telefono telefono = new Telefono("1234567890");
    private Residenza residenza = new Residenza("Via Pomezia 32, Roma RM IT");


    @Test
    public void crea_ok() {
        Cliente cliente = Cliente.crea(
            usernameCliente,
            nomeCliente,
            cognomeCliente,
            dataNascita,
            codiceFiscale,
            email,
            telefono,
            residenza
        );

        var events = cliente.popChanges();
        assert events.size() == 1;
        assert events.get(0) instanceof ClienteCreato;
        ClienteCreato event = (ClienteCreato) events.get(0);
        assertNotNull(event.getIdCliente());
        assert event.getUsernameCliente().equals(usernameCliente);
        assert event.getNomeCliente().equals(nomeCliente);
        assert event.getCognomeCliente().equals(cognomeCliente); 
        assert event.getDataNascita().equals(dataNascita);
        assert event.getCodiceFiscale().equals(codiceFiscale);
        assert event.getEmail().equals(email);
        assert event.getTelefono().equals(telefono);
        assert event.getResidenza().equals(residenza);      
        
    }
    
}
