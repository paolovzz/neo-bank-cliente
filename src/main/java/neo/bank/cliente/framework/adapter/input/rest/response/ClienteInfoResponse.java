package neo.bank.cliente.framework.adapter.input.rest.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import neo.bank.cliente.domain.models.aggregates.Cliente;

@Getter
public class ClienteInfoResponse {
   
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;
    private String codiceFiscale;
    private String telefono;
    private String residenza;
    private List<String> contiAssociati;

    public ClienteInfoResponse(Cliente cliente) {
        this.nome = cliente.getNomeCliente().nome();
        this.cognome = cliente.getCognomeCliente().cognome();
        this.dataNascita = cliente.getDataNascita().data();
        this.email = cliente.getEmail().indirizzo();
        this.codiceFiscale = cliente.getCodiceFiscale().codice();
        this.telefono = cliente.getTelefono().numero();
        this.residenza = cliente.getResidenza().residenza();
        this.contiAssociati = cliente.getContiAssociati().stream().map(iban -> iban.codice()).toList();
    }

    

}
