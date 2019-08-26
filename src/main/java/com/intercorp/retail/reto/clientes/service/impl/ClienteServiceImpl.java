package com.intercorp.retail.reto.clientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intercorp.retail.reto.clientes.model.Cliente;
import com.intercorp.retail.reto.clientes.model.KpiCliente;
import com.intercorp.retail.reto.clientes.repository.ClienteRepository;
import com.intercorp.retail.reto.clientes.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepository.insert(cliente);
	}

	@Override
	public KpiCliente getKpiClientes() {
		// TODO Auto-generated method stub
		return new KpiCliente(1.0, 2.0);
	}

	@Override
	public void deleteCliente(String id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}
}
