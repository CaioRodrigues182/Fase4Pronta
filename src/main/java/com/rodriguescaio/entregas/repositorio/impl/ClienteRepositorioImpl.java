package com.rodriguescaio.entregas.repositorio.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodriguescaio.entregas.dominio.Cliente;
import com.rodriguescaio.entregas.repositorio.ClienteRepositorioCustom;

@Repository
@Transactional(readOnly=true)
public class ClienteRepositorioImpl implements ClienteRepositorioCustom {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Cliente existeCliente(String cpf) {
		String jpql = "SELECT x FROM Cliente x WHERE cpf LIKE ':cpf'";
		Query query = em.createQuery(jpql);
		query.setParameter("cpf", cpf);
		List<Cliente> aux = query.getResultList();
		return aux.size()>0 ? aux.get(0):null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Cliente existeClienteNome(String nome) {
		String jpql = "SELECT x FROM Cliente x WHERE x.nome LIKE :nome";
		Query query = em.createQuery(jpql);
		query.setParameter("nome", nome);
		List<Cliente> aux = query.getResultList();
		return aux.size()>0 ? aux.get(0):null;
	}

}