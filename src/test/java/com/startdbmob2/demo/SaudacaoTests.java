package com.startdbmob2.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@SpringBootTest
public class SaudacaoTests {
    @Autowired
    private Saudacao saudacao;

    // Requisito 1
    // Interpolar o nome com uma saudação simples. Por exemplo, quando o nome é "Maria", o método retorna "Olá, Maria".
    @Test
    public void saudacaoSimplesTest(){
        String resultado = saudacao.saudarSimples("Maria");
        String esperado = "Olá, Maria";

        assertEquals(esperado, resultado);
    }

    // Requisito 2
    // Caso o nome não esteja preenchido, retornar "Olá, você aí".
    @ParameterizedTest
    @NullAndEmptySource
    public void saudacaoSemPrencher(String nome){
        String resultado = saudacao.saudarSimples(nome);
        String esperado = "Olá, você aí";
        
        assertEquals(esperado, resultado);
    }

    @Test
    public void saudacaoSemPrencherParametro(){
        String resultado = saudacao.saudarSimples();
        String esperado = "Olá, você aí";
        
        assertEquals(esperado, resultado);
    }
}
