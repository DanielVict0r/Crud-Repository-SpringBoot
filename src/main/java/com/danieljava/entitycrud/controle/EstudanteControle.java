package com.danieljava.entitycrud.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.danieljava.entitycrud.excecao.EstudanteNotFoundExcepiton;
import com.danieljava.entitycrud.modelo.Estudante;
import com.danieljava.entitycrud.servico.EstudanteServico;

import jakarta.validation.Valid;

//A classe EstudanteControle é quem recebe a requisição html.

//O @Controller serve para tranformar a classe em uma controladora de requisições.

//@GetMapping é para mapear uma requisição get para algum método. EX:

// Se um usuário clica em um link de um site ele esta mandando uma requisição get.




@Controller
public class EstudanteControle {
	
	@Autowired
	private EstudanteServico estudanteServico;
	
	
	@GetMapping("/")
	public String listarEstudantes(Model model)
	{
		
		List<Estudante> estudantes = estudanteServico.buscarTodosEstudante();
		model.addAttribute("listaEstudantes", estudantes); 		
		return "/lista-estudantes";
	}
	
	
	

	@PostMapping("/buscar")
	public String buscarEstudantes(Model model, @Param("nome") String nome)
	{
		if(nome==null)
		{
			return "redirect:/";
		}
		List<Estudante> estudantes = estudanteServico.buscarTodosEstudantePorNome(nome);
		model.addAttribute("listaEstudantes", estudantes); 		
		return "/lista-estudantes";
		
		
	}
	
	
	
	@GetMapping("/novo")
	public String novoEstudantes(Model model)
	{
		Estudante estudante = new Estudante();
		model.addAttribute("novoEstudante", estudante);
		return "/novo-estudante";
	}
	
	
	//Binding result é para capturar os erros de validação.
	
	
	@PostMapping("/gravar")
	public String gravarEstudante(@ModelAttribute("novoEstudante") @Valid Estudante estudante,
			BindingResult erros, 
			RedirectAttributes attributes)
	{
		if(erros.hasErrors())
		{
			return "/novo-estudante";
		}
		estudanteServico.criarEstudante(estudante);
		attributes.addFlashAttribute("mensagem", "ESTUDANTE SALVO COM SUCESSO");
		
		//se colocase novo-estudante ao invés de redirect:/novo iria para o html ao invés de ir para o metodo.
		return "redirect:/novo";
	}
	
	
	
	
	@GetMapping("/apagar/{id}")
	public String apagarEstudantes(@PathVariable("id") long id, RedirectAttributes attributes)
	{
		try 
		{
			estudanteServico.apagarEstudante(id);
		}
		catch(EstudanteNotFoundExcepiton e)
		{
			attributes.addFlashAttribute("mensagemErro",e.getMessage());
		}
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarForm(@PathVariable("id") long id, RedirectAttributes attributes,Model model)
	{
		try 
		{
			Estudante estudante = estudanteServico.buscarEstudantePorId(id);		
			model.addAttribute("objetoEstudante", estudante);
			return "/alterar-estudante";
		
		}
		catch(EstudanteNotFoundExcepiton e)
		{
			attributes.addFlashAttribute("mensagemErro",e.getMessage());
		}
		return "redirect:/";
	}
	
	
	@PostMapping("/editar/{id}")
	public String editarEstudante(@PathVariable("id") long id, 
			@ModelAttribute("objetoEstudante") @Valid Estudante estudante, 
			BindingResult erros)
	{
		if(erros.hasErrors())
		{
			estudante.setId(id);
			return "/alterar-estudante";
		}
		
		estudanteServico.alterarEstudante(estudante);
		return "redirect:/";
	}
	
	

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	