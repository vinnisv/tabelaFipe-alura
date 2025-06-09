package com.vinnis.desafio.tabelafipe.principal;

import com.vinnis.desafio.tabelafipe.model.Dados;
import com.vinnis.desafio.tabelafipe.model.Modelos;
import com.vinnis.desafio.tabelafipe.model.Veiculo;
import com.vinnis.desafio.tabelafipe.service.ConsumoAPI;
import com.vinnis.desafio.tabelafipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        System.out.println("Digite o nome do carro buscado, ou um trecho. Ex: Palio, pal..");
        var nomeVeiculo = leitura.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o codigo do modelo filtrado para fazer a avaliação: ");
        var codigoModelo = leitura.nextLine();
        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();
        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("\nTodos veiculos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);

    }
}
