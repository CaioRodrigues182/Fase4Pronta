package com.rodriguescaio.entregas.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodriguescaio.entregas.dominio.Cliente;
import com.rodriguescaio.entregas.repositorio.ClienteRepositorio;
import com.rodriguescaio.entregas.servico.excecoes.NaoEncontradoException;
import com.rodriguescaio.entregas.servico.excecoes.ServicoException;
import com.rodriguescaio.entregas.servico.excecoes.ValidacaoException;

@Service
public class ClienteServico {

	@Autowired
	private ClienteRepositorio repo;

	public void validar(Cliente x) {
		List<String> erros = new ArrayList<>();

		if (x.getNome() == null) {
			erros.add("Favor preencher o campo nome");
		}
		if (x.getEmail() == null) {
			erros.add("Favor preencher o campo Email");
		}
		if (x.getCpf() == null) {
			erros.add("Favor preencher um valor válido para o CPF");
		}

		if (!erros.isEmpty()) {
			throw new ValidacaoException("Erro de validação", erros);
		}
	}

	
	public Cliente inserir(Cliente x) throws ServicoException {
		Cliente aux = repo.existeClienteNome(x.getNome());
			if (aux != null) {
				throw new ServicoException("Já existe um Cliente com esse nome!", 1);
			}
			validar(x);
		return repo.save(x);
		}
		

	
	public Cliente atualizar(Cliente x) throws ServicoException {
		Cliente aux = repo.findOne(x.getCodCliente());
		if (aux == null) {
			throw new NaoEncontradoException("Cliente não encontrado!", 1);
		}
		validar(x);
		return repo.save(x);
	}

	public void excluir(int cod) throws ServicoException {
		Cliente cli = repo.findOne(cod);
		if (cli == null) {
			throw new NaoEncontradoException("Cliente não encontrado!", 1);
		}
		if (!cli.getEnderecos().isEmpty()) {
			throw new ServicoException("O Cliente possui endereço cadastrado", 1);
		}
			
		repo.delete(cli);
	}

	public Cliente buscar(int cod) {
		Cliente art = repo.findOne(cod);
		if (art == null) {
			throw new NaoEncontradoException("Cliente não encontrado!", 1);
		}
		return art;
	}

	public List<Cliente> buscarTodos() {
		return repo.findAll();
	}
}
