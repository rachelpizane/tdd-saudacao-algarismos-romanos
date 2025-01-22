package com.startdbmob2.demo;

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

        return String.format("Olá, %s", nome);
    }

    public String saudarSimples(){
        return "Olá, você aí";
    }
    
}
