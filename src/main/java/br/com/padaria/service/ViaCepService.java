/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.padaria.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author igoor
 */
public class ViaCepService {

    public Map<String, String> consultar(String cep) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .timeout(Duration.ofSeconds(8))
                .GET()
                .build();

        HttpResponse<String> resposta = cliente.send(
                requisicao,
                HttpResponse.BodyHandlers.ofString()
        );

        if (resposta.statusCode() != 200) {
            throw new IOException("Erro ao consultar o CEP.");
        }

        String json = resposta.body();
        Map<String, String> endereco = new HashMap<>();

        if (json.matches("(?s).*\\\"erro\\\"\\s*:\\s*\\\"?true\\\"?.*")) {
            return endereco;
        }

        endereco.put("cep", pegarValor(json, "cep"));
        endereco.put("logradouro", pegarValor(json, "logradouro"));
        endereco.put("bairro", pegarValor(json, "bairro"));
        endereco.put("cidade", pegarValor(json, "localidade"));
        endereco.put("uf", pegarValor(json, "uf"));
        return endereco;
    }

    private String pegarValor(String json, String campo) {
        Pattern padrao = Pattern.compile("\\\"" + campo + "\\\"\\s*:\\s*\\\"([^\\\"]*)\\\"");
        Matcher resultado = padrao.matcher(json);

        if (resultado.find()) {
            return resultado.group(1);
        }
        return "";
    }
}
