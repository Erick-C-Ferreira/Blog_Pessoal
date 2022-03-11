package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long>{
			/*procure tudo pelo titulo ignorando letras maiculas e minusculas*/
	public List <Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	  /*find  All      By              titulo  Containing     IgnoreCase = ignorar maisculas e minusculas*/
	/*select * from tb_postagens where titulo like "%tituloe% */
	
}
