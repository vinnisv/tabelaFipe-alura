package com.vinnis.desafio.tabelafipe.principal;

import com.vinnis.desafio.tabelafipe.model.Dados;
import com.vinnis.desafio.tabelafipe.model.Modelos;
import com.vinnis.desafio.tabelafipe.service.ConsumoAPI;
import com.vinnis.desafio.tabelafipe.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

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

        switch (opcao) {
            case "carro", "carros" -> endereco = URL_BASE + "carros/marcas";
            case "moto", "motos" -> endereco = URL_BASE + "motos/marcas";
            case "caminhao", "caminhão", "caminhoes", "caminhões" -> endereco = URL_BASE + "caminhoes/marcas";
            default -> {
                System.out.println("Opção inválida! Será considerado 'carros' como padrão.");
                endereco = URL_BASE + "carros/marcas";
            }
        }


//        if(opcao.toUpperCase().contains("carr")){
//            endereco = URL_BASE + "carros/marcas";
//        } else if (opcao.toUpperCase().contains("mot")){
//            endereco = URL_BASE + "motos/marcas";
//        }  else {
//            endereco = URL_BASE + "caminhoes/marcas";
//        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o codigo da marca para consultar: ");
        var codigoMarca = leitura.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

    }
}
