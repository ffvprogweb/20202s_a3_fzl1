package com.example.p3Teste;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.scv.model.Produto;
import com.example.scv.repository.ProdutoRepository;

@SpringBootTest
class REQ03EcluirProduto {

	@Autowired
	private ProdutoRepository repository;

	@Test
	void excluirProdutoComSucesso() {
		repository.deleteAll();
		Produto produto = new Produto("1", "Oculos de sol", "7", "200");
		repository.save(produto);
		Produto ro = repository.findByCodigo("1");
		repository.deleteById(ro.getId());
		assertThat(repository.findByCodigo("1")).isEqualTo(null);
	}
}
