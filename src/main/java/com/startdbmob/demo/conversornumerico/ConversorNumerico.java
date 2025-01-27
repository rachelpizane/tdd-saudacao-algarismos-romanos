package com.startdbmob.demo.conversornumerico;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ConversorNumerico {
    Map<String, Integer> algarismosRomanos = new HashMap<String, Integer>();
    
    public int converterRomanosParaArabicos(String algarismoRomano){
        this.setAlgarismosRomanos();

        String[] algarismoRomanoArray = algarismoRomano.toUpperCase().split("");
        int soma = 0;

        for(int i = 0; i < algarismoRomanoArray.length; i++){
            int numAtual;
            int numProximo;

            numAtual = this.algarismosRomanos.get(algarismoRomanoArray[i]);

            if(i == (algarismoRomanoArray.length - 1)) {
                soma += numAtual;
                continue;
            }
            
            numProximo = this.algarismosRomanos.get(algarismoRomanoArray[i + 1]);

            if(numAtual < numProximo){
                soma -= numAtual;
                continue;
            }

            soma += numAtual;
        }

        return soma;
    }

    private void setAlgarismosRomanos(){
        algarismosRomanos.put("I", 1);
        algarismosRomanos.put("V", 5);
        algarismosRomanos.put("X", 10);
        algarismosRomanos.put("L", 50);
        algarismosRomanos.put("C", 100);
        algarismosRomanos.put("D", 500);
        algarismosRomanos.put("M", 1000);
    }
}
