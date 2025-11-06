package neo.bank.cliente.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;


@Getter
@EqualsAndHashCode
public class NomeCliente {

    private String nome;
    public NomeCliente(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new ValidazioneException(NomeCliente.class.getSimpleName(), CodiceErrore.NOME_CLIENTE_NON_VALIDO.getCodice());
        }
        this.nome = nome;
    }
}
