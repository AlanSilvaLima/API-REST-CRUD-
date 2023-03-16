package com.algaworks.algalog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.model.Cliente;
import com.algaworks.algalog.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service        
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> clienteExistente.equals());

                if(emailEmUso) {
                    throw new
                }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
