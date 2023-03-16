package com.algaworks.algalog.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping; //é uma anotação do Spring 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController; //é uma anotação do Spring

import com.algaworks.algalog.model.Cliente;
import com.algaworks.algalog.repository.ClienteRepository;
import com.algaworks.algalog.service.CatalogoClienteService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;





@AllArgsConstructor// gera um construtor com todas as propiedades que temos na nossa classe
@RestController //serve para dizer que é um controllador Rest
@RequestMapping("/clientes")//mapeando no nivel de classe que não precisa mapear um por um nas anotações getmapping deletemapping etc
public class ClienteController {
    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;
    //essa requisição vai listar todos os dados de clientes salvos de uma vez só ira vir todos
    @GetMapping 
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
    //criando uma requisição para fazer o Get de um id um unico id que ja foi gerado pelo Post com essa requisição eu vou bucar por alguem
    @GetMapping ("/{clienteId}") //variavel de caminho
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId) // o ResponseEntity fez com que na hora de procurar um cliente não existente ele fizesse aparecer o erro explicando o que estava acontecendo no caso "cliente não cadastrado"
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());     
    }
    //criando requisição o @requestBody vai vincular o parametro do metodo no corpo da requisição no postman, vai transformar o jason em um objeto java 
    @PostMapping //mapeando o metodo para Criar
    @ResponseStatus(HttpStatus.CREATED)//anotação para retornar o status 201 created quando fizer um post
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
         return catalogoClienteService.salvar(cliente);
    }
    //declarando metodo que retorna responseentity cliente ele recebe o atualizar chamando o argumento id e o cliente 
    @PutMapping("/clienteId")//mapeando Putmapping no metodo para fazer alterações recebendo o /clienteId
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
        if (clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }       
        cliente.setId(clienteId); // se deixar sem isso vai dar erro porque vai reconhecer como criar um novo cliente por conta do "save"
        cliente = catalogoClienteService.salvar(cliente);
        
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping("/{clienteId}")//declarando @deletemapping 
    public ResponseEntity<Void> remover(Long clienteId) {
        if (clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build(); //não existe retorna 404
        }

        catalogoClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build(); //existe retorna 204 de sucesso
    }
    
}
