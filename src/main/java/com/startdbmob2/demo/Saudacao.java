package com.startdbmob2.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Saudacao {
    public String saudarSimples(){
        return "Olá, você aí";
    }

    public String saudarSimples(String nome){
        if(nome == null || nome.isEmpty()) {
            return "Olá, você aí";
        }

        if (nome.equals(nome.toUpperCase())){
            return String.format("OLÁ, %s!!!", nome);
        }

        return String.format("Olá, %s", nome);
    }

    public String saudarSimples(List<String> nomes){
        List<String> nomesFormatados = new ArrayList<>();

        int ultimoIndex;
        String mensagem;
        String mensagemGritada;
        boolean temNomeGritado;

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

        ultimoIndex = nomesFormatados.size() - 1;
        mensagem = "Olá";
        mensagemGritada = " E OLÁ";
        temNomeGritado = false;

        if(nomesFormatados.size() == 1){
            return this.saudarSimples(nomesFormatados.get(0));
        }

        for(int i = 0; i < nomesFormatados.size(); i++) {
            String nome = nomesFormatados.get(i);
            String conexaoTexto = ", ";

            String complementoMensagem;

            if(ultimoIndex == i){
                conexaoTexto = " e ";
            }

            complementoMensagem = conexaoTexto + nome;

            if(nome.equals(nome.toUpperCase())){
                temNomeGritado = true;
                mensagemGritada = mensagemGritada + complementoMensagem;
                continue;
            }

            mensagem  = mensagem + complementoMensagem;
        }

        if(!temNomeGritado){
            return mensagem;
        }

        return String.format("%s.%s!!!", mensagem, mensagemGritada);
    }
   
}
