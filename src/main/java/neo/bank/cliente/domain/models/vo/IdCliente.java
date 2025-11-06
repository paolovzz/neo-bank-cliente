package neo.bank.cliente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;


@Getter
@EqualsAndHashCode
public class IdCliente {
    
    private String id;

    public IdCliente(String id) {
        if (id == null || id.isBlank()) {
            throw new ValidazioneException(
                    IdCliente.class.getSimpleName(),
                    CodiceErrore.ID_NON_VALIDO.getCodice());
        }
        this.id = id;
    }
}
