package com.example.p3Teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.scv.model.Produto;
import com.example.scv.repository.ProdutoRepository;

@SpringBootTest
class REQ01CadastrarProduto {

	@Autowired
    private ProdutoRepository repository;

    private static Validator validator;

    @BeforeAll
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void produtoCadastrado() {
        repository.deleteAll();

        Produto produto = new Produto("1", "Oculos de sol", "7", "200");

        repository.save(produto);

        assertEquals(1, repository.count());
    }

    @Test
    void dadosEntradaValidos() {
    	Produto produto = new Produto("1", "Oculos de sol", "7", "200");

        Set<ConstraintViolation<Produto>> violations = validator.validate(produto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void descricaoEmBranco() {
    	Produto produto = new Produto("1", "", "7", "200");

        Set<ConstraintViolation<Produto>> violations = validator.validate(produto);

        assertEquals(1, violations.size());
        assertEquals("Nome não pode ficar em branco", violations.iterator().next().getMessage());
    }

    @Test
    void produtoJaCadastrado() {
    	Produto produto = new Produto("1", "Oculos de sol", "7", "200");

        repository.deleteAll();

        repository.save(produto);
        repository.save(produto);

        assertEquals(1, repository.count());
    }

}
