package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record Iban(String codice) {

    public Iban(String codice) {
        if (codice == null) {
            throw new ValidazioneException(Iban.class.getSimpleName(), CodiceErrore.IBAN_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
