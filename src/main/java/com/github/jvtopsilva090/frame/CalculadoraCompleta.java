package com.github.jvtopsilva090.frame;

import com.github.jvtopsilva090.service.HistoricoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraCompleta extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField display;
    private double valorAtual = 0;
    private String operacao = "";
    private final HistoricoService historicoService = new HistoricoService();
    private String operacaoCompleta = "";

    public CalculadoraCompleta() {
        super();
        setTitle("Calculadora CPL");
        setSize(350, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Verdana", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(400, 90));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 8, 8));
        panel.setBackground(Color.BLACK);

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "√", "^", "%"
        };

        for (String texto : botoes) {
            JButton botao = criarBotao(texto);
            panel.add(botao);
        }

        JPanel painelInferior = new JPanel(new GridLayout(1, 2, 8, 8));
        JButton botaoVoltar = criarBotaoPersonalizado("Voltar");
        botaoVoltar.addActionListener(e -> {
            this.dispose();
            new PaginaPrincipal().setVisible(true);
        });

        JButton botaoHistorico = criarBotaoPersonalizado("Histórico");
        botaoHistorico.addActionListener(e -> {
            new HistoricoFrame().setVisible(true);
        });

        painelInferior.add(botaoVoltar);
        painelInferior.add(botaoHistorico);

        add(panel, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Verdana", Font.BOLD, 20));
        botao.setBackground(new Color(128, 0, 128));
        botao.setForeground(Color.BLACK);
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setBorderPainted(false);
        botao.addActionListener(this);

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(new Color(186, 85, 211));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(new Color(128, 0, 128));
            }
        });

        return botao;
    }

    private JButton criarBotaoPersonalizado(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Verdana", Font.BOLD, 18));
        botao.setBackground(new Color(128, 0, 128));
        botao.setForeground(Color.BLACK);
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setBorderPainted(false);
        return botao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        try {
            if (comando.matches("[0-9]") || comando.equals(".")) {
                display.setText(display.getText() + comando);
                operacaoCompleta += comando;
            } else if (comando.equals("C")) {
                display.setText("");
                valorAtual = 0;
                operacao = "";
                operacaoCompleta = "";
            } else if (comando.equals("=")) {
                double novoValor = Double.parseDouble(display.getText());
                calcular(novoValor);
                display.setText(String.valueOf(valorAtual));
                operacaoCompleta += " = " + valorAtual;

                // Salvar no banco
                historicoService.adicionar(operacaoCompleta, valorAtual);
                operacao = "";
                operacaoCompleta = "";
            } else if (comando.equals("√")) {
                valorAtual = Math.sqrt(Double.parseDouble(display.getText()));
                display.setText(String.valueOf(valorAtual));
                historicoService.adicionar("√" + display.getText(), valorAtual);
            } else if (comando.equals("^")) {
                valorAtual = Math.pow(Double.parseDouble(display.getText()), 2);
                display.setText(String.valueOf(valorAtual));
                historicoService.adicionar(display.getText() + "^2", valorAtual);
            } else if (comando.equals("%")) {
                valorAtual = valorAtual * Double.parseDouble(display.getText()) / 100;
                display.setText(String.valueOf(valorAtual));
                historicoService.adicionar("porcentagem", valorAtual);
            } else {
                valorAtual = Double.parseDouble(display.getText());
                operacao = comando;
                operacaoCompleta = valorAtual + " " + operacao + " ";
                display.setText("");
            }
        } catch (NumberFormatException ex) {
            display.setText("Erro");
        }
    }

    private void calcular(double novoValor) {
        switch (operacao) {
            case "+": valorAtual += novoValor; break;
            case "-": valorAtual -= novoValor; break;
            case "*": valorAtual *= novoValor; break;
            case "/": valorAtual = (novoValor != 0) ? valorAtual / novoValor : 0; break;
        }
    }
}