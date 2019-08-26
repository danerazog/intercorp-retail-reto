package com.intercorp.retail.reto.clientes.service;

import java.util.List;

import com.intercorp.retail.reto.clientes.model.Cliente;
import com.intercorp.retail.reto.clientes.model.KpiCliente;

public interface ClienteService {

	public List<Cliente> findAll();
	public Cliente createCliente(Cliente cliente);
	public KpiCliente getKpiClientes();
	
	//Operaciones adicionales
	public void deleteCliente(String id);
}
