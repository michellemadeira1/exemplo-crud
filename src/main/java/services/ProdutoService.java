package services;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.ResourceNotFoundException;
import model.Produto;
import repository.ProdutoRepositoryOld;
import shared.ProdutoDTO;

@Service
public class ProdutoService {

	private static final ProdutoDTO produtoDTO = null;
	@Autowired
	private ProdutoRepositoryOld produtoRepository;
	
	public List<ProdutoDTO> obterTodos(){
	List<Produto> produtos = produtoRepository.findAll();
		return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class)).collect(Collectors.toList());
	}
	
	public Optional<ProdutoDTO> obterPorId(Integer id){
		Optional<Produto>produto = produtoRepository.obterPorId(id);
		
		if(produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto com id:" +id + "não encontrado");
		}
		
		ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
		
		return Optional.of(dto);
	}
	
	public ProdutoDTO adicionar(ProdutoDTO produtoDTO) {
		produtoDTO.setId(null);
		
		ModelMapper mapper = new ModelMapper();
		
		Produto produto = mapper.map(produtoDTO, Produto.class);
		
		produto = produtoRepository.save(produto);
		produtoDTO.setId(produto.getId());                          
		
		return produtoDTO;
	}
	
	public void deletar(Integer id) {
		List<Produto>produto = produtoRepository.findAll();
		if(produto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel deletar o produto com o id"+ id +"- Produto não encontrado");
		}
		produtoRepository.deletar(id); 
	}
	
	public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO) {
		produtoDTO.setId(id);
		
		ModelMapper mapper = new ModelMapper();
		
		Produto produto1 = mapper.map(produtoDTO, Produto.class);
		
		produtoRepository.save(produto1);
	
		return  produtoDTO;
  }
}

