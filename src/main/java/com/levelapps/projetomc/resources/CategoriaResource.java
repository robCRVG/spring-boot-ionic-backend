package com.levelapps.projetomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levelapps.projetomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria>listar() {
		
		Categoria categoria = new Categoria(1, "Informática");
		Categoria categoria2 = new Categoria(2, "Escrotório");
		
		
		List<Categoria> listC = new ArrayList<Categoria>();
		listC.add(categoria);
		listC.add(categoria2);

		return listC;
	}

}