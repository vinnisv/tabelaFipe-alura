package com.vinnis.desafio.tabelafipe.service;

public interface iConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
