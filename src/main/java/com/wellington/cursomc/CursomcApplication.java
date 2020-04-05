package com.wellington.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wellington.cursomc.dominio.Categoria;
import com.wellington.cursomc.dominio.Cidade;
import com.wellington.cursomc.dominio.Cliente;
import com.wellington.cursomc.dominio.Endereco;
import com.wellington.cursomc.dominio.Estado;
import com.wellington.cursomc.dominio.Pagamento;
import com.wellington.cursomc.dominio.PagamentoComBoleto;
import com.wellington.cursomc.dominio.PagamentoComCartao;
import com.wellington.cursomc.dominio.Pedido;
import com.wellington.cursomc.dominio.Produto;
import com.wellington.cursomc.dominio.enums.EstadoPagamento;
import com.wellington.cursomc.dominio.enums.TipoCliente;
import com.wellington.cursomc.repositories.CategoriaRepository;
import com.wellington.cursomc.repositories.CidadeRepository;
import com.wellington.cursomc.repositories.ClienteRepository;
import com.wellington.cursomc.repositories.EnderecoRepository;
import com.wellington.cursomc.repositories.EstadoRepository;
import com.wellington.cursomc.repositories.PagamentoRepository;
import com.wellington.cursomc.repositories.PedidoRepository;
import com.wellington.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo",est2);
		Cidade c3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "02146637790", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("877655555","876547787"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avinida matos", "105", "sala 800", "centro", "38234834", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/07/2017 19:35"),cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
	}

}

