package com.levelapps.projetomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelapps.projetomc.domain.Pedido;
import com.levelapps.projetomc.repositories.PedidoRepository;
import com.levelapps.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository PedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = PedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}