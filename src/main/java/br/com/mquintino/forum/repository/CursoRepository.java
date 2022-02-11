package br.com.mquintino.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mquintino.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);

}
