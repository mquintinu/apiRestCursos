package br.com.mquintino.forum.controller.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mquintino.forum.modelo.Topico;
import br.com.mquintino.forum.repository.TopicoRepository;

public class AtualizacaoTopicoForm {
	
	@NotNull  @Length(min = 4) 
	private String titulo;
	
	@NotNull @Length(min = 10) 
	private String mensagem;
	
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		
		Topico topico = topicoRepository.getById(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		
		return topico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
