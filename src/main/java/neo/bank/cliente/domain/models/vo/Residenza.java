package neo.bank.cliente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;


@Getter
@EqualsAndHashCode
public class Residenza {

    private String residenza;
    public Residenza(String residenza) {
        if (residenza == null || residenza.isBlank()) {
            throw new ValidazioneException(Residenza.class.getSimpleName(), CodiceErrore.RESIDENZA_NON_VALIDA.getCodice());
        }
        this.residenza = residenza;
    }
}
