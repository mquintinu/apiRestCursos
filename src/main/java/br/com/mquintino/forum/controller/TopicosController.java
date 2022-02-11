package br.com.mquintino.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mquintino.forum.controller.dto.DetalhesTopicoDto;
import br.com.mquintino.forum.controller.dto.TopicoDto;
import br.com.mquintino.forum.controller.form.AtualizacaoTopicoForm;
import br.com.mquintino.forum.controller.form.TopicoForm;
import br.com.mquintino.forum.modelo.Topico;
import br.com.mquintino.forum.repository.CursoRepository;
import br.com.mquintino.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;	
	
	// SELECIONAR TODOS OS CAMPOS
	@GetMapping()
	public List<TopicoDto> lista(String nomeCurso) {					
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else
		{
			List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
			return TopicoDto.converter(topicos);
		}				
	}
	
	// SELECIONAR POR ID
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDto> detalhar (@PathVariable Long id) {
		//Topico topico = topicoRepository.getById(id);
		Optional<Topico> topico = topicoRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	// INCLUIR UM NOVO REGISTRO
	@PostMapping
	@Transactional  // >> isso aqui faz o processo commitar após encerrar o método
	public ResponseEntity<TopicoDto> cadastrar (@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
		Topico topico = topicoForm.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	// ATUALIZAR UM REGISTRO
	@PutMapping("/{id}")
	@Transactional 
	public ResponseEntity<TopicoDto> atualizar (@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm topicoForm){
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {		
			Topico topico = topicoForm.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		
		return ResponseEntity.notFound().build();
				
	}
	
	// DELETAR UM REGISTRO
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover (@PathVariable Long id){
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {		
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();				
	}
	
	
}
