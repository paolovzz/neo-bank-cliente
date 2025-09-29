package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record CognomeCliente(String cognome){

    public CognomeCliente(String cognome) {
        if (cognome == null || cognome.isBlank()) {
            throw new ValidazioneException(CognomeCliente.class.getSimpleName(), CodiceErrore.COGNOME_CLIENTE_NON_VALIDO.getCodice());
        }
        this.cognome = cognome;
    }
}
