package com.danieljava.entitycrud.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljava.entitycrud.excecao.EstudanteNotFoundExcepiton;
import com.danieljava.entitycrud.modelo.Estudante;
import com.danieljava.entitycrud.repositorio.EstudanteRepositorio;

//Esta classe serve para pegar um objeto do tipo EstudanteRepositorio e usar seus metodos.

//@Service é para poder mandar um obejeto para a camada repositorio.

//@Autowired serve para injetar um objeto no estudanteRepositorio quando necessário.

@Service
public class EstudanteServico {
	
	@Autowired
	private EstudanteRepositorio estudanteRepositorio;
	
	
	public Estudante criarEstudante(Estudante estudante)
	{
		return estudanteRepositorio.save(estudante);
	}
	


	
	
	public List<Estudante> buscarTodosEstudante()
	{
		return estudanteRepositorio.findAll();
	}
	
	
	
	

	
	
	
	
	
	
	public Estudante buscarEstudantePorId(Long id) throws EstudanteNotFoundExcepiton{
		Optional<Estudante> opt = estudanteRepositorio.findById(id);
		if(opt.isPresent()) 
		{
			return opt.get();
		}
		else 
		{
			throw new EstudanteNotFoundExcepiton("Estudante com id "+ id+ " não existe");
		}
	}
	
	
	
	
	
	
	
	
//---------------------------------------------------------------------------------------------------

	
	
	
	public void apagarEstudante(Long id)throws EstudanteNotFoundExcepiton
	{
		Estudante estudante = buscarEstudantePorId(id);
		estudanteRepositorio.delete(estudante);
	}
	
	
	public Estudante alterarEstudante(Estudante estudante)
	{
		return estudanteRepositorio.save(estudante);
	}

	
	
	public List<Estudante> buscarTodosEstudantePorNome(String nome)
	{
		return estudanteRepositorio.findByNomeContainingIgnoreCase(nome);
	}

	
	
}
