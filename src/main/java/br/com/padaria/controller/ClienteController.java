package br.com.padaria.controller;

import br.com.padaria.model.Cliente;
import br.com.padaria.service.ViaCepService;
import java.util.Map;

/**
 *
 * @author guilh
 */
public class ClienteController {

    private ViaCepService viaCepService;

    public ClienteController() {
        this.viaCepService = new ViaCepService();
    }

    // Recebe o CEP digitado, consulta o serviço e retorna o objeto Cliente com os dados
    public Cliente buscarEnderecoPorCep(String cepDigitado){
        String cepLimpo = cepDigitado.replaceAll("[^0-9]", "");

        if (cepLimpo.length() != 8){
            return null;
        }

        try {
            Map<String, String> dados = viaCepService.consultar(cepLimpo);

            if (dados.isEmpty()) {
                return null;
            }

            Cliente cliente = new Cliente();
            cliente.setCep(dados.get("cep"));
            cliente.setRua(dados.get("logradouro"));
            cliente.setBairro(dados.get("bairro"));
            cliente.setCidade(dados.get("cidade"));
            cliente.setUf(dados.get("uf"));

            return cliente;
        } catch (Exception e) {
            System.err.println("Erro ao buscar CEP; " + e.getMessage());
            return null;
        }
    }
}