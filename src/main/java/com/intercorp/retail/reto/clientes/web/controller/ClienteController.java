package com.intercorp.retail.reto.clientes.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intercorp.retail.reto.clientes.model.Cliente;
import com.intercorp.retail.reto.clientes.model.KpiCliente;
import com.intercorp.retail.reto.clientes.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listclientes")
	public List<Cliente> getAllClientes() {
		return clienteService.findAll();
	}
	
	@PostMapping("/creacliente")
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteService.createCliente(cliente);
	}
	
	@GetMapping("/kpideclientes")
	public KpiCliente getKpiClientes() {
		return clienteService.getKpiClientes();
	}
	
	@DeleteMapping("/eliminarcliente/{id}")
	public void deleteCliente(@PathVariable String id) {
		clienteService.deleteCliente(id);
	}
}
