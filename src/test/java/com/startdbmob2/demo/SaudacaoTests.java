package com.startdbmob2.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaudacaoTests {
    @Autowired
    private Saudacao saudacao;
    // Exercício 1 - Saudações

    // Usando TDD, escreva uma função pura para cumprimentar o usuário, evoluindo o código com os requisitos na ordem abaixo.
    // (Uma função pura é aquela para qual o resultado é sempre o mesmo dada a mesma entrada e não tem efeitos colaterais; ou seja não utiliza informações que não receba por parâmetro, como variáveis globais, e não altera nenhum dado, inclusive os parâmetros). 

    // Requisito 1
    // Interpolar o nome com uma saudação simples. Por exemplo, quando o nome é
    // "Maria", o método retorna "Olá, Maria".
    @Test
    public void saudarComUmNome() {
        String resultado = saudacao.saudar("Maria");
        String esperado = "Olá, Maria";

        assertEquals(esperado, resultado);
    }

    // Requisito 2
    // Caso o nome não esteja preenchido, retornar "Olá, você aí".
    @ParameterizedTest
    @NullAndEmptySource
    public void saudarSemPrencher(String nome) {
        String resultado = saudacao.saudar(nome);
        String esperado = "Olá, você aí";

        assertEquals(esperado, resultado);
    }

    @Test
    public void saudarSemPrencherParametro() {
        String resultado = saudacao.saudar();
        String esperado = "Olá, você aí";

        assertEquals(esperado, resultado);
    }

    // Requisito 3
    // Se o nome for gritado (em maiúsculas), retornar gritando também "OLÁ, MARIA!!!".
    @Test
    public void saudarGritando() {
        String resultado = saudacao.saudar("MARIA");
        String esperado = "OLÁ, MARIA!!!";

        assertEquals(esperado, resultado);
    }

    // Requisito 4
    // Para entrada com dois nomes, retornar os nomes separados com "e". Por exemplo
    // para ["Maria, "Nina"], retornar "Olá, Maria e Nina".
    @Test
    public void saudarComListaDeNomesComDoisNomes() {
        List<String> nomes = new ArrayList<String>();

        nomes.add("Maria");
        nomes.add("Nina");

        String resultado = saudacao.saudar(nomes);
        String esperado = "Olá, Maria e Nina";

        assertEquals(esperado, resultado);
    }

    @Test
    public void saudarComListaDeNomesComUmNome() {
        List<String> nomes = new ArrayList<String>();

        nomes.add("Maria");

        String resultado = saudacao.saudar(nomes);
        String esperado = "Olá, Maria";

        assertEquals(esperado, resultado);
    }

    @Test
    public void saudarComListaDeNomesVazia() {
        List<String> nomes = new ArrayList<String>();

        String resultado = saudacao.saudar(nomes);
        String esperado = "Olá, você aí";

        assertEquals(esperado, resultado);
    }

    // Requisito 5
    // Para entrada com mais de dois nomes, separar os nomes com vírgula e o último
    // com "e".
    @Test
    public void saudarComListaDeNomesComMaisDeDoisNomes() {
        List<String> nomes = new ArrayList<String>();

        nomes.add("Maria");
        nomes.add("Nina");
        nomes.add("Carla");

        String resultado = saudacao.saudar(nomes);
        String esperado = "Olá, Maria, Nina e Carla";

        assertEquals(esperado, resultado);
    }

    @Test
    public void saudacaoComListaDeNomesComUmNome() {
        List<String> nomes = new ArrayList<String>();

        nomes.add("Maria");

        String resultado = saudacao.saudar(nomes);
        String esperado = "Olá, Maria";

        assertEquals(esperado, resultado);
    }

    // Requisito 6
    // Separar nomes gritados em outra saudação. Por exemplo para ["Maria", "LÚCIA",
    // "Nina"] retornar "Olá Maria e Nina. E OLÁ, LÚCIA!!!"
    @ParameterizedTest
    @CsvSource({
            "Maria, LÚCIA, Nina, CARLA, Rachel, VITORIA,'Olá, Maria, Nina e Rachel. E OLÁ, LÚCIA, CARLA E VITORIA!!!'",
            "Maria, LÚCIA, Nina, CARLA, RACHEL, VITORIA,'Olá, Maria e Nina. E OLÁ, LÚCIA, CARLA, RACHEL E VITORIA!!!'",
            "Maria, LÚCIA, NINA, CARLA, RACHEL, VITORIA,'Olá, Maria. E OLÁ, LÚCIA, NINA, CARLA, RACHEL E VITORIA!!!'",
            "Maria, LÚCIA, Nina, CARLA, Rachel, Vitoria, 'Olá, Maria, Nina, Rachel e Vitoria. E OLÁ, LÚCIA E CARLA!!!'",
            "Maria, Lúcia, Nina, CARLA, Rachel, Vitoria, 'Olá, Maria, Lúcia, Nina, Rachel e Vitoria. E OLÁ, CARLA!!!'"
    })
    public void saudarComListaDeNomesComNomesGritados(String nome1, String nome2,String nome3, String nome4, String nome5, String nome6, String esperado) {
        List<String> nomes = new ArrayList<String>();

        nomes.add(nome1);
        nomes.add(nome2);
        nomes.add(nome3);
        nomes.add(nome4);
        nomes.add(nome5);
        nomes.add(nome6);

        String resultado = saudacao.saudar(nomes);
        assertEquals(esperado, resultado);
    }
    
    // Requisito 7
    // Se algum dos nomes incluir vírgula, separe e trate como nomes separados. Por
    // exemplo para ["Maria, Lúcia", "Nina"], retornar "Olá, Maria, Lúcia e Nina".
    @Test
    public void saudacaoComListaDeNomesComNomesComVirgulas() {
        List<String> nomes = new ArrayList<String>();

        nomes.add("Maria, Lúcia");
        nomes.add("Nina, Carla");

        String resultado = saudacao.saudar(nomes);
        String esperado = "Olá, Maria, Lúcia, Nina e Carla";

        assertEquals(esperado, resultado);
    }

    // Requisito 8
    // Permita que a entrada informe vírgulas intencionalmente usando caracteres de escape. Isso pode ser feito da mesma forma que em arquivos CSV, com aspas duplas em volta da entrada. Por exemplo para ["Maria", "\"Nina, Lúcia\""], retornar "Olá, Maria e Nina, Lúcia".
    @Test
    public void saudacaoComListaDeNomesComEscapes() {
        List<String> nomes = new ArrayList<String>();

        nomes.add("Maria");
        nomes.add("Carla, Thaína");
        nomes.add("\"Nina, Lúcia\"");
        
        String resultado = saudacao.saudar(nomes);
        String esperado = "Olá, Maria, Carla, Thaína e Nina, Lúcia";

        assertEquals(esperado, resultado);
    }
    
}
