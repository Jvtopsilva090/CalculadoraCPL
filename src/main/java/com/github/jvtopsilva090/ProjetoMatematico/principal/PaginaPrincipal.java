package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    public PaginaPrincipal() {
        // Certifique-se de que o JFrame foi inicializado corretamente
        super(); // Chama o construtor da classe JFrame

        // Configuração básica da janela
        setTitle("Página Principal");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 10, 10)); // Layout com dois botões

        // Configurar fundo da janela
        getContentPane().setBackground(Color.BLACK);

        // Criar painel para os botões
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.BLACK); // Fundo do painel preto

        // Criar botão para abrir a calculadora
        JButton botaoCalculadora = new JButton("Abrir Calculadora");
        botaoCalculadora.setFont(new Font("Verdana", Font.BOLD, 20)); // Fonte elegante
        botaoCalculadora.setBackground(new Color(128, 0, 128)); // Cor roxa
        botaoCalculadora.setForeground(Color.BLACK); // Texto branco
        botaoCalculadora.setFocusPainted(false); // Remover o destaque de foco
        botaoCalculadora.setOpaque(true); // Garante que a cor de fundo do botão é aplicada
        botaoCalculadora.setBorderPainted(false); // Remove a borda padrão
        botaoCalculadora.addActionListener(this);

        // Adicionar o botão ao painel
        panel.add(botaoCalculadora);

        // Criar outro botão (pode adicionar mais opções aqui)
        JButton botaoOutraOpcao = new JButton("Outra Opção");
        botaoOutraOpcao.setFont(new Font("Verdana", Font.BOLD, 20)); // Fonte elegante
        botaoOutraOpcao.setBackground(new Color(128, 0, 128)); // Cor roxa
        botaoOutraOpcao.setForeground(Color.BLACK); // Texto branco
        botaoOutraOpcao.setFocusPainted(false); // Remover o destaque de foco
        botaoOutraOpcao.setOpaque(true); // Garante que a cor de fundo do botão é aplicada
        botaoOutraOpcao.setBorderPainted(false); // Remove a borda padrão
        botaoOutraOpcao.addActionListener(this);

        // Adicionar o botão ao painel
        panel.add(botaoOutraOpcao);

        // Adicionar painel à janela principal
        add(panel);

        // Somente agora que a interface foi montada, configurar a localização
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Abrir Calculadora")) {
            // Fecha a janela principal e abre a calculadora
            this.dispose();
            new CalculadoraCompleta().setVisible(true);
        } else if (comando.equals("Outra Opção")) {
            // Fecha a janela principal e exibe a outra opção
            this.dispose();
            JOptionPane.showMessageDialog(this, "Outra opção selecionada.");
        }
    }

    public static void main(String[] args) {
        // Cria e exibe a página principal
        PaginaPrincipal pagina = new PaginaPrincipal();
        pagina.setVisible(true);
    }
}