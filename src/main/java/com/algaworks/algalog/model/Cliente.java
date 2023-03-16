package com.algaworks.algalog.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)// incluindo as propiedades mas apenas as propiedades de forma explicitas
@Getter
@Setter//gerei os getters e setters com os codigos boilerplate pode ser evitado usando a biblioteca loombok
@Entity //essa classe representa uma entidade, no jakarta persistence uma entidade ela esta associada a uma tabela do banco de dados
//criado a classe Cliente
public class Cliente {
    // criado as propiedades id,nome,email,telefone para criar metodos
    @EqualsAndHashCode.Include// apenas a propiedade id vai ser levados em consideraçao na hora de gerar os metodos equals e hascode pelo lombok 
    @Id //essa anotação define a chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //aqui nos definimos a estrategia de geracao da chave para usar a forma nativa do banco de dados auto incremente
    private Long id;

    @NotBlank// validação para notnull e nome vazio tambem
    @Size(max = 60)// passando uma validação dizendo que o valor maximo de nome de cracteries é 60
    private String nome;
    @NotBlank
    @Email//valida se o email esta correto que tenha a sintaxe correta tipo o @ . etc
    @Size(max = 255)
    private String email;
    @NotBlank
    @Size(max = 20)
    private String telefone;
    public Object equals() {
        return null;
    }
    


    
}
