package neo.bank.cliente.domain.models.vo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import neo.bank.cliente.domain.models.exceptions.ValidazioneException;

public class DataNascitaTest {


    @Test
    public void creazione_ok() {
        assertDoesNotThrow(()-> new DataNascita(LocalDate.now()));
    }

    @Test
    public void creazione_ko() {
        assertThrows(ValidazioneException.class, ()-> new DataNascita(null));
    }
}
