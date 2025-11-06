package neo.bank.cliente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

@Getter
@EqualsAndHashCode
public class CodiceCliente{

    private String codice;
    public CodiceCliente(String codice) {
        if (codice == null || codice.isBlank()) {
            throw new ValidazioneException(CodiceCliente.class.getSimpleName(), CodiceErrore.CODICE_CLIENTE_NON_VALIDO.getCodice());
        }
        this.codice = codice;
    }
}
