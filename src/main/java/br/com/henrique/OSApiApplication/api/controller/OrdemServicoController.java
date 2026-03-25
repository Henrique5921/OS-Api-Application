/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.OSApiApplication.api.controller;

import br.com.henrique.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.com.henrique.OSApiApplication.domain.model.OrdemServico;
import br.com.henrique.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.com.henrique.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesi3dia
 */
@RestController

public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping("/ordem-servico")
    public List<OrdemServico> listas() {

        return ordemServicoRepository.findAll();
        //return clienteRepository.findByNome("KGe");
        //return clienteRepository.findByNomeContaining("Silva");
    }

    @GetMapping("/ordem-servico/{clienteID}")
    public ResponseEntity<List<OrdemServico>> buscar(@PathVariable Long clienteID) {
        List<OrdemServico> ordensCliente = ordemServicoRepository.findByClienteId(clienteID);
        if (ordensCliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ordensCliente);
        }
    }

    @GetMapping("/ordem-servico/buscar-ordem/{ordemServicoID}")
    public ResponseEntity<OrdemServico> buscarOrdemServico(@PathVariable Long ordemServicoID) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoID);
        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ordem-servico")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }

    @PutMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordemServicoID,
            @RequestBody OrdemServico ordemServico) {

        Optional<OrdemServico> osAtual = ordemServicoRepository.findById(ordemServicoID);

        if (osAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        OrdemServico ordemServicoExistente = osAtual.get();

        ordemServicoExistente.setDescricao(ordemServico.getDescricao());
        ordemServicoExistente.setCliente(ordemServico.getCliente());
        ordemServicoExistente.setPreco(ordemServico.getPreco());

        if (ordemServico.getStatus() != null) {
            ordemServicoExistente.setStatus(ordemServico.getStatus());
        }

        ordemServicoExistente = ordemServicoService.salvar(ordemServicoExistente);
        return ResponseEntity.ok(ordemServico);
    }
    
    @PutMapping("/ordem-servico/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(@PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO) {
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
                ordemServicoID,
                statusDTO.status());
        
        if (optOS.isPresent()) {
            return ResponseEntity.ok(optOS.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<Void> excluir(@PathVariable Long ordemServicoID) {
        if (!ordemServicoRepository.existsById(ordemServicoID)) {
            return ResponseEntity.notFound().build();
        }
        ordemServicoService.excluir(ordemServicoID);
        return ResponseEntity.noContent().build();
    }
}
