package neo.bank.cliente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

@Getter
@EqualsAndHashCode
public class CodiceFiscale{

    private String codice;
    public CodiceFiscale(String codice) {
        String regexCF = "^[A-Z]{6}[0-9]{2}[A-EHLMPRST][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
        if (codice == null || !codice.toUpperCase().matches(regexCF)) {
            throw new ValidazioneException(CodiceFiscale.class.getSimpleName(), CodiceErrore.CODICE_FISCALE_NON_VALIDO.getCodice());
        }
        this.codice = codice.toUpperCase();
    }
}
