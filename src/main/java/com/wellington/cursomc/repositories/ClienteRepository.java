package com.wellington.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellington.cursomc.dominio.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
