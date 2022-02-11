package br.com.mquintino.forum.controller.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mquintino.forum.modelo.Curso;
import br.com.mquintino.forum.modelo.Topico;
import br.com.mquintino.forum.repository.CursoRepository;

public class TopicoForm {

	@NotNull  @Length(min = 4) 
	private String titulo;
	
	@NotNull @Length(min = 10) 
	private String mensagem;
	
	@NotNull
	private String nomeCurso;
	
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
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
	
	
	
}
