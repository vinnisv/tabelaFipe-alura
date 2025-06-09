package com.vinnis.desafio.tabelafipe.principal;

import com.vinnis.desafio.tabelafipe.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    public void exibeMenu() {
        var menu = """
                 *** OPÇÕES ***
                 Carro 
                 Moto 
                 Caminhão
                 
                 Digite umas das opções para consultar: 
                 """;
        System.out.println(menu);
        var opcao = leitura.nextLine();
        String endereco;

        if(opcao.toUpperCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toUpperCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas";
        }  else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);
    }
}
