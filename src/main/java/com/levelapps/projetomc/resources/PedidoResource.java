package com.levelapps.projetomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levelapps.projetomc.domain.Pedido;
import com.levelapps.projetomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService PedidoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Pedido Pedido = PedidoService.buscar(id);
		return ResponseEntity.ok().body(Pedido);

	}

}