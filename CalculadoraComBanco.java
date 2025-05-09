package principal;

import java.awt.BorderLayout;
import java.util.List;

//--- Classe 1: CalculadoraComBanco.java ---
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CalculadoraComBanco extends JFrame {
 private static final long serialVersionUID = -8195616590164733151L;
private JTextField campoExpressao;
 private JTextArea areaHistorico;
 private BancoDeDados banco;
 private HistoricoManager historicoManager;

 public CalculadoraComBanco() {
     banco = new BancoDeDados();
     historicoManager = new HistoricoManager(banco);
     inicializarUI();
 }

 private void inicializarUI() {
     setTitle("Calculadora com Histórico");
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setSize(400, 300);
     setLocationRelativeTo(null);

     campoExpressao = new JTextField();
     JButton botaoCalcular = new JButton("Calcular");
     JButton botaoLimpar = new JButton("Limpar Histórico");

     areaHistorico = new JTextArea();
     areaHistorico.setEditable(false);

     JScrollPane scroll = new JScrollPane(areaHistorico);

     botaoCalcular.addActionListener(e -> calcular());
     botaoLimpar.addActionListener(e -> limparHistorico());

     JPanel painelBotoes = new JPanel();
     painelBotoes.add(botaoCalcular);
     painelBotoes.add(botaoLimpar);

     add(campoExpressao, BorderLayout.NORTH);
     add(scroll, BorderLayout.CENTER);
     add(painelBotoes, BorderLayout.SOUTH);

     carregarHistorico();
     setVisible(true);
 }

 private void calcular() {
     String expressao = campoExpressao.getText();
     try {
         String resultado = AvaliadorExpressao.avaliar(expressao);
         String linha = expressao + " = " + resultado;
         historicoManager.adicionarAoHistorico(linha);
         carregarHistorico();
         campoExpressao.setText("");
     } catch (Exception e) {
         JOptionPane.showMessageDialog(this, "Erro na expressão: " + e.getMessage());
     }
 }

 private void limparHistorico() {
     historicoManager.limparHistorico();
     carregarHistorico();
 }

 private void carregarHistorico() {
     List<String> historico = historicoManager.getHistorico();
     areaHistorico.setText(String.join("\n", historico));
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(CalculadoraComBanco::new);
 }
}
