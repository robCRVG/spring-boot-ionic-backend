package com.levelapps.projetomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.levelapps.projetomc.domain.Categoria;
import com.levelapps.projetomc.dto.CategoriaDTO;
import com.levelapps.projetomc.repositories.CategoriaRepository;
import com.levelapps.projetomc.services.exceptions.DataIntegrityException;
import com.levelapps.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);		
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível exlcuir uma categoria que possui produto");
		}				
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
}