package com.startdbmob2.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ConversorNumericoTests {
    @Autowired
    ConversorNumerico conversor;

    // Exercício 2 - Algarismos Romanos

    // Escreva uma função para converter algarismos romanos em arábicos. (Para simplificar o exercício, não vamos testar se é um número válido)

    // I - 1
    // V - 5
    // X - 10 
    // L - 50 
    // C - 100
    // D - 500
    // M - 1000

    // Números são formados combinando os algarismos e somando seu valor. Em geral os algarismos são colocados em ordem de valor, começando com os valores mais altos. Quando valores menores precedem valores maiores, os valores menores são subtraídos dos maiores e o resultado é somado ao total.

    // Exemplos
    // MMVI = 1000 + 1000 + 5 + 1 = 2006
    // MCMXLIV	= 1000 + (1000 - 100) + (50 - 10) + (5 - 1) = 1944
    @ParameterizedTest
    @CsvSource({
            "MMVI,2006",
            "MCMXLIV,1944",
            "MMMDLXIV,3564",
            "V,5",
            "IV,4",
    })
    void converterRomanosParaArabicosComSucesso(String numeroRomano, int numeroArabico) {
        int resultado = conversor.converterRomanosParaArabicos(numeroRomano);

        assertThat(resultado).isEqualTo(numeroArabico);
    }
}
