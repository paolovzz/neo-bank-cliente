package neo.bank.cliente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;


@Getter
@EqualsAndHashCode
public class Email {

    private String indirizzo;
    
    public Email(String indirizzo) {
        String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (indirizzo == null || !indirizzo.matches(regexEmail)) {
            throw new ValidazioneException(Email.class.getSimpleName(), CodiceErrore.EMAIL_NON_VALIDA.getCodice());
        }
        this.indirizzo = indirizzo;
    }
}
