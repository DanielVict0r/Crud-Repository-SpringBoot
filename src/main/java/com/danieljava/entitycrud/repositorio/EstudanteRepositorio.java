package com.danieljava.entitycrud.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danieljava.entitycrud.modelo.Estudante;

public interface EstudanteRepositorio extends JpaRepository<Estudante, Long> 
{
	
	
	//O metodo abaixo tem um nome formado por predicados que são palavras chave do SpringData JPA
	List<Estudante> findByNomeContainingIgnoreCase(String nome);
	

}
