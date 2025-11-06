package neo.bank.cliente.domain.models.vo;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;


@Getter
@EqualsAndHashCode
public class DataNascita {

    private LocalDate data;
    public DataNascita(LocalDate data) {
        if (data == null) {
            throw new ValidazioneException(DataNascita.class.getSimpleName(), CodiceErrore.DATA_NASCITA_NON_PUO_ESSERE_NULL.getCodice());
        }
        this.data = data;
    }
}
