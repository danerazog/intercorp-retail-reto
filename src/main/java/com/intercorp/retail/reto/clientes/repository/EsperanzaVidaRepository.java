package com.intercorp.retail.reto.clientes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.intercorp.retail.reto.clientes.model.EsperanzaVida;

public interface EsperanzaVidaRepository extends MongoRepository<EsperanzaVida, String> {

}
