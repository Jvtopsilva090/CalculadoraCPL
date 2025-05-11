package com.github.jvtopsilva090.service;

import com.github.jvtopsilva090.entity.Historico;
import com.github.jvtopsilva090.repository.HistoricoRepository;

import java.math.BigDecimal;
import java.util.List;

public class HistoricoService {

    private final HistoricoRepository historicoRepository;

    public HistoricoService() {
        historicoRepository = new HistoricoRepository();
    }

    public void adicionar(String operacao, BigDecimal resultado) {
        historicoRepository.inserir(new Historico(operacao, resultado));
    }

    public List<Historico> obterTodos() {
        return historicoRepository.listar();
    }
 }