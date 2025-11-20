package neo.bank.cliente.domain.models.vo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public class NumeroCartaTest {


    @Test
    public void creazione_ok() {
        assertDoesNotThrow(()-> new NumeroCarta("4242 4242 4242 4242"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void creazione_ko(String cf) {
        assertThrows(ValidazioneException.class, ()-> new NumeroCarta(cf));
    }
}
