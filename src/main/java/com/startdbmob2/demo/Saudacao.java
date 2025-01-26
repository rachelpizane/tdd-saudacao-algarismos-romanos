package com.startdbmob2.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class Saudacao {
    public String saudar(){
        return "Olá, você aí";
    }

    public String saudar(String nome){
        if(nome == null || nome.isEmpty()) {
            return "Olá, você aí";
        }

        if (nome.equals(nome.toUpperCase())){
            return String.format("OLÁ, %s!!!", nome);
        }

        return String.format("Olá, %s", nome);
    }

    public String saudar(List<String> nomes){
        List<String> nomesFormatados = new ArrayList<>();
        List<String> nomesFormatadosSimples = new ArrayList<>();
        List<String> nomesFormatadosGritados = new ArrayList<>();

        String mensagemSimples;
        String mensagemGritada;

        if(nomes.size() == 0){
            return this.saudar();
        }

        nomesFormatados = formatarListaNomes(nomes);
        
        if(nomesFormatados.size() == 1){
            return this.saudar(nomesFormatados.get(0));
        }

        for(int i = 0; i < nomesFormatados.size(); i++){
            String nome = nomes.get(i);

            if(nome.equals(nome.toUpperCase())){
                nomesFormatadosGritados.add(nome);
                continue;
            }

            nomesFormatadosSimples.add(nome); 
        }

        mensagemSimples = construirMensagem(nomesFormatadosSimples, false);

        if(nomesFormatadosGritados.size() == 0){
            return mensagemSimples;
        }

        mensagemGritada = construirMensagem(nomesFormatadosGritados, true);

        return String.format("%s. E %s!!!", mensagemSimples, mensagemGritada);
    }

    private List<String> formatarListaNomes(List<String> nomes){
        List<String> nomesFormatados = new ArrayList<>();

        for(int i = 0; i < nomes.size(); i++) {
            String item = nomes.get(i);
            boolean contemEscape = item.contains("\"");

            if(item.contains(",") && !contemEscape){ // Se conter uma virgula e não conter uma escape.
                String[] itens = item.split((",")); // Separar nomes pela virgula

                for(int j = 0; j < itens.length; j++) { // Acessar cada nome da nova array
                    itens[j] = itens[j].trim(); // Retirar espaços dos cantos;
                    nomesFormatados.add(itens[j]); // Incluir na nova lista.
                }
                
                continue; // Seguir para o próximo loop.
            }
             
            item = item.replace("\"", ""); // Retirar os escapes (Se tiver);

            nomesFormatados.add(item); // Incluir na nova lista.
        }

        return nomesFormatados;
    }

    private String construirMensagem(List<String> nomes, boolean ehNomeGritado){
        int ultimoIndex = nomes.size() - 1;
        String mensagem = "Olá";

        for(int i = 0; i < nomes.size(); i++) {
            String nome = nomes.get(i);
            String conexaoTexto = ", ";

            if(nomes.size() != 1 && i == ultimoIndex){
                conexaoTexto = " e ";
            }

            mensagem  = mensagem + conexaoTexto + nome;
        }

        if(ehNomeGritado){
            return mensagem.toUpperCase();
        }

        return mensagem;

    }
   
}
