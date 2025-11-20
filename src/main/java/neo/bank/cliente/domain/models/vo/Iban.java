package neo.bank.cliente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;


@Getter
@EqualsAndHashCode
public class Iban {

    private String codice;
    public Iban(String codice) {
        if (codice == null || codice.isBlank())  {
            throw new ValidazioneException(Iban.class.getSimpleName(), CodiceErrore.IBAN_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.codice = codice;
    }
}
