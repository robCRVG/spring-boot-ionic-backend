package com.levelapps.projetomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.levelapps.projetomc.domain.Categoria;
import com.levelapps.projetomc.domain.Cidade;
import com.levelapps.projetomc.domain.Cliente;
import com.levelapps.projetomc.domain.Endereco;
import com.levelapps.projetomc.domain.Estado;
import com.levelapps.projetomc.domain.ItemPedido;
import com.levelapps.projetomc.domain.Pagamento;
import com.levelapps.projetomc.domain.PagamentoBoleto;
import com.levelapps.projetomc.domain.PagamentoCartao;
import com.levelapps.projetomc.domain.Pedido;
import com.levelapps.projetomc.domain.Produto;
import com.levelapps.projetomc.domain.enums.EstadoPagamento;
import com.levelapps.projetomc.domain.enums.TipoCliente;
import com.levelapps.projetomc.repositories.CategoriaRepository;
import com.levelapps.projetomc.repositories.CidadeRepository;
import com.levelapps.projetomc.repositories.ClienteRepository;
import com.levelapps.projetomc.repositories.EnderecoRepository;
import com.levelapps.projetomc.repositories.EstadoRepository;
import com.levelapps.projetomc.repositories.ItemPedidoRepository;
import com.levelapps.projetomc.repositories.PagamentoRepository;
import com.levelapps.projetomc.repositories.PedidoRepository;
import com.levelapps.projetomc.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired PagamentoRepository pagamentoRepository;
	
	@Autowired ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Periférico");
		Categoria cat4 = new Categoria(null, "Software");
		Categoria cat5 = new Categoria(null, "Hardware");
		Categoria cat6 = new Categoria(null, "Ferramentas");
		Categoria cat7 = new Categoria(null, "Insumos");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 90.00);

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Goiás");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Goiânia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "98575777855", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("62991155445", "62666444466"));

		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apt.303", "Jardim", "74140100", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida matos", "654", "Sala 800", "Centro", "74120022", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2021 10:30"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("05/10/2021 07:30"), cli1, e2);
		
		Pagamento pagt1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagt1);
		
		Pagamento pagt2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2020 21:10"), null);
		ped2.setPagamento(pagt2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagt1, pagt2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
