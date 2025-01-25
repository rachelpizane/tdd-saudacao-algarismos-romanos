package com.startdbmob2.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConversorNumericoTests {
    @Autowired
    ConversorNumerico conversor;

    @ParameterizedTest
    @CsvSource({
            "MMVI,2006",
            "MCMXLIV,1944",
            "V,5",
            "IV,4",
    })
    void testConverterRomanosParaArabicos(String numeroRomano, int numeroArabico) {
        int resultado = conversor.converterRomanosParaArabicos(numeroRomano);

        assertEquals(numeroArabico, resultado);
    }
}
