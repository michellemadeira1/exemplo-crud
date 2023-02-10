package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import exception.ResourceNotFoundException;
import model.Produto;

@Repository
public class ProdutoRepositoryOld {

	private List<Produto> produtos = new ArrayList<Produto>();
	private Integer ultimoId = 0;

	public List<Produto> obterTodos() {
		return produtos;
	}

	public Optional<Produto> obterPorId(Integer id) {
		return produtos.stream().findFirst();
	}

	public Produto adicionar(Produto produto) {
		ultimoId++;
		produto.setId(ultimoId);
		produtos.add(produto);
		return produto;
	}

	public void deletar(Integer id) {
		produtos.remove(id);
	}

	public Produto atualizar(Produto produto) {
		Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

		if (produtoEncontrado.isEmpty()) {
			throw new ResourceNotFoundException("Produto não encontrado");
		}
		deletar(produto.getId());

		produtos.add(produto);

		return produto;

	}
}