package com.intercorp.retail.reto.clientes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.intercorp.retail.reto.clientes.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
	
}
