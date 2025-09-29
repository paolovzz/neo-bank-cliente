package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record Email(String indirizzo){

    public Email(String indirizzo) {
        String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (indirizzo == null || !indirizzo.matches(regexEmail)) {
            throw new ValidazioneException(DatiAnagraficiCliente.class.getSimpleName(), CodiceErrore.EMAIL_NON_VALIDA.getCodice());
        }
        this.indirizzo = indirizzo;
    }
}
