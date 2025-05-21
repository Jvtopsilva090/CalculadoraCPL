package com.github.jvtopsilva090.frame;

import com.github.jvtopsilva090.service.HistoricoService;
import com.github.jvtopsilva090.utils.TipoOperacaoEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.math.BigDecimal;

public class CalculadoraCompleta extends JFrame implements ActionListener {

    @Serial
    private static final long serialVersionUID = 1L;

    private final JTextField display;
    private final HistoricoService historicoService;

    private boolean possuiDecimal;

    private String operacaoCompleta;
    private BigDecimal valorTotal;

    private BigDecimal valorAtual;
    private BigDecimal valorDecimalAtual;

    private TipoOperacaoEnum tipoOperacaoAtual;

    public CalculadoraCompleta() {
        super();

        this.operacaoCompleta = "";
        this.historicoService = new HistoricoService();
        this.tipoOperacaoAtual = TipoOperacaoEnum.NONE;
        this.valorTotal = BigDecimal.ZERO;
        this.valorAtual = BigDecimal.ZERO;
        this.valorDecimalAtual = BigDecimal.ZERO;

        this.display = new JTextField() {{
            setEditable(false);
            setHorizontalAlignment(JTextField.RIGHT);
            setPreferredSize(new Dimension(400, 90));
            setFont(new Font("Verdana", Font.BOLD, 28));
        }};

        JPanel digitalPanel = new JPanel() {{
            setLayout(new GridLayout(5, 4, 8, 8));
            setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));
            setBackground(Color.WHITE);
        }};

        String[] botoes = {
            "1", "2", "3", "/",
            "4", "5", "6", "*",
            "7", "8", "9", "-",
            ".", "0", "=", "+",
            "C", "√", "^", "%"
        };

        for (String texto : botoes) {
            JButton botao = criarBotao(texto);
            digitalPanel.add(botao);
        }

        JButton botaoVoltar = criarBotaoPersonalizado("Sair");
        JButton botaoHistorico = criarBotaoPersonalizado("Histórico");

        botaoVoltar.addActionListener(e -> this.dispose());

        botaoHistorico.addActionListener(e -> new HistoricoFrame().setVisible(true));

        JPanel bottomPanel = new JPanel() {{
            setLayout(new GridLayout(1, 2, 8, 8));
            setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            setBackground(Color.WHITE);

            add(botaoVoltar);
            add(botaoHistorico);
        }};

        this.setTitle("Calculadora CPL");
        this.setSize(350, 550);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        this.add(display, BorderLayout.NORTH);
        this.add(digitalPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto) {{
            setFont(new Font("Verdana", Font.BOLD, 20));
            setBackground(new Color(223, 223, 223));
            setForeground(Color.BLACK);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(true);
        }};

        botao.addActionListener(this);

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(new Color(182, 182, 182));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(new Color(223, 223, 223));
            }
        });

        return botao;
    }

    private JButton criarBotaoPersonalizado(String texto) {
        JButton botao = new JButton(texto) {{
            setFont(new Font("Verdana", Font.BOLD, 20));
            setBackground(new Color(223, 223, 223));
            setForeground(Color.BLACK);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(true);
        }};

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(new Color(182, 182, 182));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(new Color(223, 223, 223));
            }
        });

        return botao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String comando = e.getActionCommand();

            if (comando.matches("[0-9]")) {
                if (possuiDecimal) {
                    valorDecimalAtual = valorDecimalAtual.multiply(BigDecimal.TEN).add(new BigDecimal(comando));
                } else {
                    valorAtual = valorAtual.multiply(BigDecimal.TEN).add(new BigDecimal(comando));
                }

                System.out.printf("valor atual: %s%n", valorAtual.toString());
                System.out.printf("valor decimal atual: %s%n", valorDecimalAtual.toString());

                display.setText(display.getText() + comando);

                return;
            }

            switch (comando) {
                case "=":
                    operacaoCompleta = display.getText();
                    realizarCalculo();
                    historicoService.adicionar(operacaoCompleta, valorTotal);
                    limparOperacaoParcial();
                    break;
                case "C":
                    limparOperacao();
                    break;
                case ".":
                    if (!possuiDecimal) {
                        possuiDecimal = true;
                        display.setText(display.getText() + comando);
                    }
                    break;
                default:
                    adicionarOperador(TipoOperacaoEnum.from(comando));
            }

//            } else if (comando.equals("=")) {
//                double novoValor = Double.parseDouble(display.getText());
//                calcular(novoValor);
//                display.setText(String.valueOf(valorAtual));
//                operacaoCompleta += " = " + valorAtual;
//
//                // Salvar no banco
////                historicoService.adicionar(operacaoCompleta, valorAtual);
//                operacao = "";
//                operacaoCompleta = "";
//            }
        } catch (NumberFormatException ex) {
            display.setText("Erro");
        }
    }

    private void realizarCalculo() {
        BigDecimal valorAtualCompleto = valorAtual.add(new BigDecimal(String.format("0.%s", valorDecimalAtual.toString())));

        if (tipoOperacaoAtual.equals(TipoOperacaoEnum.NONE)){
            valorTotal = valorTotal.add(valorAtualCompleto);
        }

        limparOperacaoParcial();
    }

    private void adicionarOperador(TipoOperacaoEnum operador) {

    }

    private void limparOperacaoParcial() {
        valorAtual = BigDecimal.ZERO;
        valorDecimalAtual = BigDecimal.ZERO;
        possuiDecimal = false;
        tipoOperacaoAtual = TipoOperacaoEnum.NONE;
    }

    private void limparOperacao() {
        operacaoCompleta = "";
        possuiDecimal = false;
        tipoOperacaoAtual = TipoOperacaoEnum.NONE;
        valorTotal = BigDecimal.ZERO;
        valorAtual = BigDecimal.ZERO;
        valorDecimalAtual = BigDecimal.ZERO;

        display.setText("");
    }
}