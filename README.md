# 🚗 Desafio Java - Consulta Tabela FIPE

Este projeto Java foi desenvolvido como parte de um desafio de programação, com o objetivo de construir uma aplicação de linha de comando que realiza consultas de preços médios de veículos (carros, motos ou caminhões) com base na Tabela FIPE.

## 🔍 Descrição

A aplicação interage com a API pública da [Tabela FIPE](https://parallelum.com.br/fipe/api/v1/) e simula o processo de pesquisa feito no próprio site oficial, porém com melhorias na experiência em linha de comando.

O usuário pode:

1. Selecionar o tipo de veículo: carro, moto ou caminhão.
2. Escolher a **marca** pelo código da lista retornada.
3. Escolher o **modelo** filtrando pelo nome (ex: "Palio").
4. Verificar todos os **anos disponíveis** daquele modelo.
5. Ver todas as **avaliações disponíveis** para os anos listados.

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Jackson** (para desserialização JSON)
- **Scanner** (para interação via terminal)
- **HTTP Client** (para consumo da API FIPE)

## 📦 Estrutura do Projeto
com.vinnis.desafio.tabelafipe
├── model # Representações dos dados da API (Marca, Modelo, Anos, etc.)
├── service # Classe para consumo da API e conversão de dados
├── principal # Classe Principal com o fluxo da aplicação


## ✅ Funcionalidades

- Consulta dinâmica à API da FIPE.
- Listagem de marcas, modelos e anos com ordenação.
- Filtro por nome do modelo.
- Exibição do valor médio do veículo por ano.

## 📡 Fonte da API
[FONTE DA API](https://deividfortuna.github.io/fipe/)

