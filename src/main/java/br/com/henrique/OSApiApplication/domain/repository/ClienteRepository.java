/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.henrique.OSApiApplication.domain.repository;

import br.com.henrique.OSApiApplication.domain.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sesi3dia
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    List<Cliente> findByNomeContaining(String nome);
}
