package com.wellington.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellington.cursomc.dominio.Categoria;
import com.wellington.cursomc.repositories.CategoriaRepository;
import com.wellington.cursomc.services.exception.ObjectNotFoundException;



@Service
public class CategoriaService {

	@Autowired // instancia automaticamento
	private CategoriaRepository repo;
	
	
	public Categoria buscar(Integer id)  {
	   Optional<Categoria> obj =repo.findById(id);
	  // return obj.orElse(null);
	   return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não Encontrado! Id: "+ id + ", Tipo: "+ Categoria.class.getName()));
	}
	
}
