package com.github.jvtopsilva090.service;

import com.github.jvtopsilva090.model.HistoricoModel;
import com.github.jvtopsilva090.repository.HistoricoDAO;

import java.util.List;

public class HistoricoService {
    private final HistoricoDAO dao;

    public HistoricoService() {
        dao = new HistoricoDAO();
    }

    public void adicionar(String operacao, double resultado) {
        HistoricoModel h = new HistoricoModel(operacao, resultado);
        dao.inserir(h);
    }

    public List<HistoricoModel> obterTodos() {
        return dao.listar();
    }
 }