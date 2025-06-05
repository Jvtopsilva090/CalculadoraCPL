package com.github.jvtopsilva090.frame;

import com.github.jvtopsilva090.entity.FragmentoOperacao;
import com.github.jvtopsilva090.service.HistoricoService;
import com.github.jvtopsilva090.service.OperacaoService;
import com.github.jvtopsilva090.utils.TipoOperacaoEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraCompleta extends JFrame implements ActionListener {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Color BUTTON_COLOR = new Color(128, 0, 128);
    private final Color BUTTON_HOVER_COLOR = new Color(186, 85, 211);

    private final Color PANEL_COLOR = new Color(19, 19, 19);
    private final Color DISPLAY_COLOR = new Color(30, 30, 30);
    private final Color DISPLAY_TEXT_COLOR = new Color(153, 153, 153);

    private final JTextField display;

    private final HistoricoService historicoService = new HistoricoService();
    private final OperacaoService operacaoService = new OperacaoService();

    private BigDecimal valorAtual = BigDecimal.ZERO;
    private BigDecimal valorDecimalAtual = BigDecimal.ZERO;

    private boolean possuiDecimal = false;

    private List<FragmentoOperacao> operacaoCompleta = new ArrayList<>();

    public CalculadoraCompleta() {
        super();

        this.display = new JTextField() {{
            setEditable(false);
            setHorizontalAlignment(JTextField.RIGHT);
            setPreferredSize(new Dimension(400, 90));
            setFont(new Font("Verdana", Font.BOLD, 28));
            setBackground(DISPLAY_COLOR);
            setBorder(BorderFactory.createLineBorder(DISPLAY_COLOR, 0));
            setForeground(DISPLAY_TEXT_COLOR);
        }};

        JPanel digitalPanel = new JPanel() {{
            setLayout(new GridLayout(5, 4, 8, 8));
            setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));
            setBackground(PANEL_COLOR);
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
            setBackground(PANEL_COLOR);

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
            setBackground(BUTTON_COLOR);
            setForeground(Color.BLACK);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(true);
        }};

        botao.addActionListener(this);

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(BUTTON_COLOR);
            }
        });

        return botao;
    }

    private JButton criarBotaoPersonalizado(String texto) {
        JButton botao = new JButton(texto) {{
            setFont(new Font("Verdana", Font.BOLD, 20));
            setBackground(BUTTON_COLOR);
            setForeground(Color.BLACK);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(true);
        }};

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(BUTTON_COLOR);
            }
        });

        return botao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(operacaoCompleta);

        try {
            String comando = e.getActionCommand();

            if (comando.matches("[0-9]")) {
                if (!possuiDecimal && display.getText().contains(".")) {
                    display.setText(comando);
                    return;
                }

                if (possuiDecimal) {
                    valorDecimalAtual = valorDecimalAtual.multiply(BigDecimal.TEN).add(new BigDecimal(comando));
                } else {
                    valorAtual = valorAtual.multiply(BigDecimal.TEN).add(new BigDecimal(comando));
                }

                display.setText(display.getText() + comando);
                return;
            }

            switch (comando) {
                case "+" -> adicionarFragmentoOperacao(TipoOperacaoEnum.SOMA);
                case "-" -> adicionarFragmentoOperacao(TipoOperacaoEnum.SUBTRACAO);
                case "*" -> adicionarFragmentoOperacao(TipoOperacaoEnum.MULTIPLICACAO);
                case "/" -> adicionarFragmentoOperacao(TipoOperacaoEnum.DIVISAO);
                case "√" -> adicionarFragmentoOperacao(TipoOperacaoEnum.RAIZ_QUADRADA);
                case "^" -> adicionarFragmentoOperacao(TipoOperacaoEnum.POTENCIA);
                case "%" -> adicionarFragmentoOperacao(TipoOperacaoEnum.PORCENTAGEM);
                case "C" -> limparTela();
                case "." -> {
                    if (!possuiDecimal) {
                        possuiDecimal = true;
                        display.setText(display.getText() + comando);
                    }
                }
                case "=" -> {
                    BigDecimal valorAtualComDecimal = valorAtual.add(new BigDecimal(String.format("0.%s", valorDecimalAtual.toString())));
                    operacaoCompleta.add(new FragmentoOperacao(valorAtualComDecimal, TipoOperacaoEnum.NONE));

                    BigDecimal valorTotal = operacaoService.calcularListaOperacao(operacaoCompleta);
                    String textoOperacao = operacaoService.converterOperacaoCompletaToString(operacaoCompleta);

                    limparTela();

                    valorAtual = valorTotal;
                    display.setText(valorTotal.toString());
                    historicoService.adicionar(textoOperacao, valorTotal);
                }
            }
        } catch (NumberFormatException ex) {
            display.setText("Erro");
        }
    }

    private void adicionarFragmentoOperacao(TipoOperacaoEnum tipoOperacaoFragmento) {
        BigDecimal valorAtualComDecimal = valorAtual.add(new BigDecimal(String.format("0.%s", valorDecimalAtual.toString())));
        operacaoCompleta.add(new FragmentoOperacao(valorAtualComDecimal, tipoOperacaoFragmento));
        display.setText(display.getText() + tipoOperacaoFragmento.toDisplay());
        limparCache();
    }

    private void limparCache() {
        valorAtual = BigDecimal.ZERO;
        valorDecimalAtual = BigDecimal.ZERO;
        possuiDecimal = false;
    }

    private void limparTela() {
        limparCache();
        display.setText("");
        operacaoCompleta = new ArrayList<>();
    }
}