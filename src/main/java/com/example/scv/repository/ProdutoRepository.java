package com.example.scv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scv.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByCodigo(String codigo);

	List<Produto> findAllByDescricaoIgnoreCaseContaining(String descricao);
	
}
