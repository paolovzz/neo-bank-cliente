package neo.bank.cliente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;


@Getter
@EqualsAndHashCode
public class IdContoCorrente {

    private String id;
    public IdContoCorrente(String id) {
        if (id == null || id.isBlank()) {
            throw new ValidazioneException(
                IdContoCorrente.class.getSimpleName(),
                CodiceErrore.ID_NON_VALIDO.getCodice()
            );
        }
        this.id = id;
    }
}
