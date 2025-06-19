package com.github.jvtopsilva090.frame;

import com.github.jvtopsilva090.entity.Historico;
import com.github.jvtopsilva090.service.HistoricoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.Serial;
import java.util.List;

public class HistoricoFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 7699574658963268423L;

    private final HistoricoService service;
    private final JTable tabela;

    //Interface completa do campo Histórico
    public HistoricoFrame() {
        super("Histórico de Cálculos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        service = new HistoricoService();

        tabela = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        add(btnFechar, BorderLayout.SOUTH);

        carregarTabela();
    }

    //Responsável pela obtencao dos calculos feitos anteriormente e exibicao em tela
    private void carregarTabela() {
        String[] colunas = { "ID", "Operação", "Resultado" };
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        List<Historico> historicos = service.obterTodos();
        for (Historico h : historicos) {
            Object[] linha = {
                h.getId(),
                h.getOperacao(),
                h.getResultado()
            };
            modelo.addRow(linha);
        }

        tabela.setModel(modelo);
    }
}
