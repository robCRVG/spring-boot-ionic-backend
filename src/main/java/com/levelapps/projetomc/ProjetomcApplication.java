package com.levelapps.projetomc;

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
import com.levelapps.projetomc.domain.Produto;
import com.levelapps.projetomc.domain.enums.TipoCliente;
import com.levelapps.projetomc.repositories.CategoriaRepository;
import com.levelapps.projetomc.repositories.CidadeRepository;
import com.levelapps.projetomc.repositories.ClienteRepository;
import com.levelapps.projetomc.repositories.EnderecoRepository;
import com.levelapps.projetomc.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(ProjetomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 600.00);
		Produto p3 = new Produto(null, "Mouse", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

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

	}

}
