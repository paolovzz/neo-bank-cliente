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
    private List<String> carteAssociate;

    public ClienteInfoResponse(Cliente cliente) {
        this.nome = cliente.getNomeCliente().getNome();
        this.cognome = cliente.getCognomeCliente().getCognome();
        this.dataNascita = cliente.getDataNascita().getData();
        this.email = cliente.getEmail().getIndirizzo();
        this.codiceFiscale = cliente.getCodiceFiscale().getCodice();
        this.telefono = cliente.getTelefono().getNumero();
        this.residenza = cliente.getResidenza().getResidenza();
        this.contiAssociati = cliente.getContiAssociati().stream().map(iban -> iban.getCodice()).toList();
        this.carteAssociate = cliente.getCarteAssociate().stream().map(numeroCarta -> numeroCarta.getNumero()).toList();
    }

    

}
