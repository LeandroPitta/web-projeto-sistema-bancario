package br.ada.caixa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PostMapping("/cadastro")
    public void cadastrarCliente() {

    }

    @GetMapping("/{idCliente}")
    public void pesquisarCliente() {

    }

    @GetMapping
    public void pesquisarClientes() {

    }

}
