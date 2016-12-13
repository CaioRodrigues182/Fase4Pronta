package com.rodriguescaio.entregas.repositorio;

import com.rodriguescaio.entregas.dominio.Cliente;

public interface ClienteRepositorioCustom {


	Cliente existeCliente(String cpf);
	Cliente existeClienteNome(String nome);
}
