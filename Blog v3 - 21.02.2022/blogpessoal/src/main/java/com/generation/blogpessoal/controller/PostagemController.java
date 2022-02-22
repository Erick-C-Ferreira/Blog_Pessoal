package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		
		/*select * from tb_postagens;*/
	
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());		
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		
	}	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem (@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
									/*Status 201 = Created*/
	} /*@PutMapping
		public ResponseEntity<Postagem> putPostagem (@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK)
					.body(postagemRepository.save(postagem));								00000000000000000000		
	}*/
	
	@PutMapping
	public ResponseEntity<Postagem>putPostagem(@RequestBody Postagem postagem){
	  return postagemRepository.findById(postagem.getId())
			  .map(resposta -> ResponseEntity.ok().body(postagemRepository.save(postagem)))
			  /*.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))*/
			  .orElse(ResponseEntity.notFound().build());}
	
	/*@PutMapping(value ="/{id}")
		public ResponseEntity<Postagem> putPostagem (@PathVariable ("id") Long id,@Valid @RequestBody Postagem postagem){
			return postagemRepository.findById(id).map(record ->{
				record.setTexto(postagem.getTexto());
				record.setTitulo(postagem.getTitulo());
				Postagem update = postagemRepository.save(record);
				return ResponseEntity.status(HttpStatus.OK).body(update);
			}).orElse(ResponseEntity.notFound().build());}	*/	
		
	@DeleteMapping(path = "{id}")
	public ResponseEntity <?> deletePostagem (@PathVariable Long id) {
		return postagemRepository.findById(id)
				.map(record -> {
			postagemRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();})
				.orElse(ResponseEntity.notFound().build());	
		}	
	
	/*@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable Long id) {
		postagemRepository.deleteById(id);}*/
	
}