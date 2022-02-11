package br.com.mquintino.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import br.com.mquintino.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	// o Underline diz que dentro de Curso vai ter o atributo Nome
	List<Topico> findByCurso_Nome(String nomeCurso); 

	/*
	 * Caso eu queria mudar o nome da convenção "findBy", preciso passar a Query manualmente.
	 * @Query("SELECT t from Topico t WHERE t.curso.nome = :nomeCurso") List<Topico>
	 * carregarPorNomedoCurso(@Param("nomeCurso") String nomeCurso);
	 */

}
