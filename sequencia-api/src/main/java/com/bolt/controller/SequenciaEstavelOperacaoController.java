/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.controller;

import com.bolt.service.SequenciaEstavelOperacaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller da SequenciaEstavelOperacao
 * @author priscilaluz
 */
@RestController
@RequestMapping("/api/sequenciaEstavel")
public class SequenciaEstavelOperacaoController {

    @Autowired
    private SequenciaEstavelOperacaoService sequenciaEstavelOperacaoService;

    @PostMapping("cadastrar")
    public ResponseEntity<List<Integer>> cadastrarSequenciasEstaveisOperacoes(@RequestBody List<String> sequenciasInstaveis) {
        return ResponseEntity.ok(sequenciaEstavelOperacaoService.salvarQntOperacoesEstabilizar(sequenciasInstaveis));
    }
}
