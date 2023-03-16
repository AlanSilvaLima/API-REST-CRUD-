package com.algaworks.algalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.model.Cliente;

@Repository//cliente repository é o que gerencia a entidade no caso a entidade cliente.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome); //implementando com suporte para metodos de consultas
    Optional<Cliente> findByEmail(String email); // email especificado
    List<Cliente> findByNomeContaining(String nome); // esse você faz consulta por exemplo "silva" "lima" "soares" etc...
}
