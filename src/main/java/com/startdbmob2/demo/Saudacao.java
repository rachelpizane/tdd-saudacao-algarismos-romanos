package com.startdbmob2.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Saudacao {

    public String saudarSimples(String nome){
        String mensagemPadrao = "Olá, você aí";
        if(nome == null) {
            return mensagemPadrao;
        }

        if(nome.isEmpty()){
            return mensagemPadrao;
        }

        if (nome.equals(nome.toUpperCase())){
            return String.format("OLÁ, %s!!!", nome);
        }

        return String.format("Olá, %s", nome);
    }

    public String saudarSimples(){
        return "Olá, você aí";
    }

    public String saudarSimples(List<String> nomes){
        List<String> nomesFormatados = new ArrayList<>();

        int ultimoIndex;
        
        String mensagem = "Olá";
        String mensagemGritada = " E OLÁ";
        boolean temNomeGritado = false;

        

        for(int i = 0; i < nomes.size(); i++) {
            String item = nomes.get(i);

            if(item.contains(",")){
                String[] itens = item.split((","));

                for(int j = 0; j < itens.length; j++) {
                    itens[j] = itens[j].trim();
                    nomesFormatados.add(itens[j]);
                }
                continue;
            }
            nomesFormatados.add(item);

        }

        ultimoIndex = nomesFormatados.size() - 1;

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
            System.out.print(mensagem);
        }

        if(!temNomeGritado){
            return mensagem;
        }

        return String.format("%s.%s!!!", mensagem, mensagemGritada);
    }
   
}
