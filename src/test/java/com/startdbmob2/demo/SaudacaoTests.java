package com.startdbmob2.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@SpringBootTest
public class SaudacaoTests {
    @Autowired
    private Saudacao saudacao;

    // Requisito 1
    // Interpolar o nome com uma saudação simples. Por exemplo, quando o nome é
    // "Maria", o método retorna "Olá, Maria".
    @Test
    public void saudacaoSimplesTest() {
        String resultado = saudacao.saudarSimples("Maria");
        String esperado = "Olá, Maria";

        assertEquals(esperado, resultado);
    }

    // Requisito 2
    // Caso o nome não esteja preenchido, retornar "Olá, você aí".
    @ParameterizedTest
    @NullAndEmptySource
    public void saudacaoSemPrencher(String nome) {
        String resultado = saudacao.saudarSimples(nome);
        String esperado = "Olá, você aí";

        assertEquals(esperado, resultado);
    }

    @Test
    public void saudacaoSemPrencherParametro() {
        String resultado = saudacao.saudarSimples();
        String esperado = "Olá, você aí";

        assertEquals(esperado, resultado);
    }

    // Requisito 3
    // Se o nome for gritado (em maiúsculas), retornar gritando também "OLÁ,
    // MARIA!!!".
    @Test
    public void saudacaoGritada() {
        String resultado = saudacao.saudarSimples("MARIA");
        String esperado = "OLÁ, MARIA!!!";

        assertEquals(esperado, resultado);
    }

    // Requisito 4
    // Para entrada com dois nomes, retornar os nomes separados com "e". Por exemplo
    // para ["Maria, "Nina"], retornar "Olá, Maria e Nina".
    @Test
    public void saudacaoComListaDeNomesComDoisNomes() {
        List<String> nomes = new ArrayList<>();

        nomes.add("Maria");
        nomes.add("Nina");

        String resultado = saudacao.saudarSimples(nomes);
        String esperado = "Olá, Maria e Nina";

        assertEquals(esperado, resultado);
    }

    // Requisito 5
    // Para entrada com mais de dois nomes, separar os nomes com vírgula e o último
    // com "e".
    @Test
    public void saudacaoComListaDeNomesComMaisDeDoisNomes() {
        List<String> nomes = new ArrayList<>();

        nomes.add("Maria");
        nomes.add("Nina");
        nomes.add("Carla");

        String resultado = saudacao.saudarSimples(nomes);
        String esperado = "Olá, Maria, Nina e Carla";

        assertEquals(esperado, resultado);
    }

    @Test
    public void saudacaoComListaDeNomesComUmNome() {
        List<String> nomes = new ArrayList<>();

        nomes.add("Maria");

        String resultado = saudacao.saudarSimples(nomes);
        String esperado = "Olá, Maria";

        assertEquals(esperado, resultado);
    }

    // Requisito 6
    // Separar nomes gritados em outra saudação. Por exemplo para ["Maria", "LÚCIA",
    // "Nina"] retornar "Olá Maria e Nina. E OLÁ, LÚCIA!!!"
    @Test
    public void saudacaoComListaDeNomesComNomesGritados() {
        List<String> nomes = new ArrayList<>();

        nomes.add("Maria");
        nomes.add("LÚCIA");
        nomes.add("Nina");

        String resultado = saudacao.saudarSimples(nomes);
        String esperado = "Olá, Maria e Nina. E OLÁ, LÚCIA!!!";

        assertEquals(esperado, resultado);
    }

    // Requisito 7
    // Se algum dos nomes incluir vírgula, separe e trate como nomes separados. Por
    // exemplo para ["Maria, Lúcia", "Nina"], retornar "Olá, Maria, Lúcia e Nina".
    @Test
    public void saudacaoComListaDeNomesComNomesComVirgulas() {
        List<String> nomes = new ArrayList<>();

        nomes.add("Maria, Lúcia");
        nomes.add("Nina, Carla");

        String resultado = saudacao.saudarSimples(nomes);
        String esperado = "Olá, Maria, Lúcia, Nina e Carla";

        assertEquals(esperado, resultado);
    }

    // Requisito 8
    // Permita que a entrada informe vírgulas intencionalmente usando caracteres de escape. Isso pode ser feito da mesma forma que em arquivos CSV, com aspas duplas em volta da entrada. Por exemplo para ["Maria", "\"Nina, Lúcia\""], retornar "Olá, Maria e Nina, Lúcia".
    @Test
    public void saudacaoComListaDeNomesComEscapes() {
        List<String> nomes = new ArrayList<>();

        nomes.add("Maria");
        nomes.add("Carla, Thaína");
        nomes.add("\"Nina, Lúcia\"");
        
        String resultado = saudacao.saudarSimples(nomes);
        String esperado = "Olá, Maria, Carla, Thaína e Nina, Lúcia";

        assertEquals(esperado, resultado);
    }
    
}
