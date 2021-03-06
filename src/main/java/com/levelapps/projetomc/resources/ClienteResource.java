package com.levelapps.projetomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.levelapps.projetomc.domain.Cliente;
import com.levelapps.projetomc.dto.ClienteDTO;
import com.levelapps.projetomc.dto.ClienteEnderecoDTO;
import com.levelapps.projetomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteEnderecoDTO objDto){
		Cliente obj = clienteService.fromDTO(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").buildAndExpand(obj.getId()).toUri();		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){		
		Cliente obj = clienteService.fromDTO(objDto);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){		
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, 
													   @RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage, 
													   @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
													   @RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<Cliente> list = clienteService.findPage(page, linesPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
		
	}
}
