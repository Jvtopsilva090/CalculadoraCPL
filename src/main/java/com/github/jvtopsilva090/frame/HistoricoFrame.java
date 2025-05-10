package com.github.jvtopsilva090.frame;

import com.github.jvtopsilva090.model.HistoricoModel;
import com.github.jvtopsilva090.service.HistoricoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoricoFrame extends JFrame {
    private static final long serialVersionUID = 7699574658963268423L;
	private JTable tabela;
    private HistoricoService service;

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

    private void carregarTabela() {
        String[] colunas = { "ID", "Operação", "Resultado" };
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        List<HistoricoModel> historicos = service.obterTodos();
        for (HistoricoModel h : historicos) {
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
