package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalculadoraCompleta extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // Campo de texto para exibir o resultado e as operações
    private JTextField display;

    // Armazena o valor atual e a ultima operação escolhida
    private double valorAtual = 0;
    private String operacao = "";

    public CalculadoraCompleta() {
        // Certifique-se de que o JFrame foi inicializado corretamente
        super(); // Chama o construtor da classe JFrame

        // Configuração básica da janela
        setTitle("Calculadora CPL");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout da Calculadora
        setLayout(new BorderLayout());

        // Criar o campo de texto (Display)
        display = new JTextField();
        display.setEditable(false);
        display.setBackground(Color.WHITE); // Fundo do display preto
        display.setForeground(Color.BLACK); // Texto branco no display
        display.setFont(new Font("Verdana", Font.BOLD, 28)); // Fonte mais elegante
        display.setHorizontalAlignment(JTextField.RIGHT); // Alinha o texto à direita
        display.setPreferredSize(new Dimension(400, 90)); // Ajustar o tamanho do display
        add(display, BorderLayout.NORTH);

        // Cria Botões da calculadora
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 8, 8)); // Grid com espaçamento ajustado
        panel.setBackground(Color.BLACK); // Fundo do painel preto

        String[] botoes = {
            "7", "8", "9", "/",    
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "√", "^", "%",
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Verdana", Font.BOLD, 20)); // Fonte elegante e adequada
            botao.setBackground(new Color(128, 0, 128)); // Cor roxa nos botões
            botao.setForeground(Color.BLACK); // Texto preto nos botões
            botao.setFocusPainted(false); // Remover o destaque de foco nos botões
            botao.setOpaque(true); // Garante que a cor de fundo do botão é aplicada
            botao.setBorderPainted(false); // Remove a borda padrão do botão
            botao.addActionListener(this);

            // Adicionando animação com MouseListener
            botao.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    botao.setBackground(new Color(186, 85, 211)); // Cor mais clara quando o mouse passa
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    botao.setBackground(new Color(128, 0, 128)); // Retorna à cor original
                }
            });

            panel.add(botao);
        }
        
     // Criar botão Voltar
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Verdana", Font.BOLD, 20)); // Fonte elegante
        botaoVoltar.setBackground(new Color(128, 0, 128)); // Cor roxa
        botaoVoltar.setForeground(Color.BLACK); // Texto branco
        botaoVoltar.setFocusPainted(false); // Remover o destaque de foco
        botaoVoltar.setOpaque(true); // Garante que a cor de fundo do botão é aplicada
        botaoVoltar.setBorderPainted(false); // Remove a borda padrão
        botaoVoltar.addActionListener(e -> {
            // Fecha a calculadora e volta para a página principal
            this.dispose();
            new PaginaPrincipal().setVisible(true);
        });

        add(panel, BorderLayout.CENTER);
        add(botaoVoltar, BorderLayout.SOUTH);
        
        
        // Somente agora que a interface foi montada, configurar a localização
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            // Verifica se o comando é um número ou ponto decimal
            if (comando.matches("[0-9]") || comando.equals(".")) {
                display.setText(display.getText() + comando);
            } else if (comando.equals("C")) {
                // Limpar o display
                display.setText("");
                valorAtual = 0;
                operacao = "";
            } else if (comando.equals("=")) {
                // Realiza a operação e exibe o resultado
                calcular(Double.parseDouble(display.getText()));
                display.setText(String.valueOf(valorAtual));
                operacao = "";
            } else if (comando.equals("√")) {
                // Calcula a raiz quadrada
                valorAtual = Math.sqrt(Double.parseDouble(display.getText()));
                display.setText(String.valueOf(valorAtual));
            } else if (comando.equals("^")) {
                // Eleva o número à potência 2
                valorAtual = Math.pow(Double.parseDouble(display.getText()), 2);
                display.setText(String.valueOf(valorAtual));
            } else {
                // Operação aritmética escolhida
                valorAtual = Double.parseDouble(display.getText());
                operacao = comando;
                display.setText("");
            }
        } catch (NumberFormatException ex) {
            display.setText("Erro");
        }
    }

    private void calcular(double numero) {
        switch (operacao) {
            case "+":
                valorAtual += numero;
                break;
            case "-":
                valorAtual -= numero;
                break;
            case "*":
                valorAtual *= numero;
                break;
            case "%":
                valorAtual %= numero;
                break;
            case "/":
                if (numero != 0) {
                    valorAtual /= numero;
                } else {
                    display.setText("Erro: Div/0");
                }
                break;
        }
    }

    public static void main(String[] args) {
        // Cria e exibe a calculadora
        CalculadoraCompleta calc = new CalculadoraCompleta();
        calc.setVisible(true);
    }
}