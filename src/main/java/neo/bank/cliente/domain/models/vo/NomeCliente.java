package neo.bank.cliente.domain.models.vo;

import neo.bank.cliente.domain.models.enums.CodiceErrore;
import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public record NomeCliente(String nome){

    public NomeCliente(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new ValidazioneException(NomeCliente.class.getSimpleName(), CodiceErrore.NOME_CLIENTE_NON_VALIDO.getCodice());
        }
        this.nome = nome;
    }
}
