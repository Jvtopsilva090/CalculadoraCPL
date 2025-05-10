package com.github.jvtopsilva090.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PaginaPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    public PaginaPrincipal() {
        super();

        setTitle("Página Principal");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 10, 10));
        getContentPane().setBackground(Color.BLACK);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.BLACK);

        JButton botaoCalculadora = new JButton("Abrir Calculadora");
        botaoCalculadora.setFont(new Font("Verdana", Font.BOLD, 20));
        botaoCalculadora.setBackground(new Color(128, 0, 128));
        botaoCalculadora.setForeground(Color.BLACK);
        botaoCalculadora.setFocusPainted(false);
        botaoCalculadora.setOpaque(true);
        botaoCalculadora.setBorderPainted(false);
        botaoCalculadora.addActionListener(this);
        panel.add(botaoCalculadora);

        JButton botaoOutraOpcao = new JButton("Outra Opção");
        botaoOutraOpcao.setFont(new Font("Verdana", Font.BOLD, 20));
        botaoOutraOpcao.setBackground(new Color(128, 0, 128));
        botaoOutraOpcao.setForeground(Color.BLACK);
        botaoOutraOpcao.setFocusPainted(false);
        botaoOutraOpcao.setOpaque(true);
        botaoOutraOpcao.setBorderPainted(false);
        botaoOutraOpcao.addActionListener(this);
        panel.add(botaoOutraOpcao);

        add(panel);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Abrir Calculadora")) {
            this.dispose();
            new CalculadoraCompleta().setVisible(true);
        } else if (comando.equals("Outra Opção")) {
            this.dispose();
            JOptionPane.showMessageDialog(this, "Outra opção selecionada.");
        }
    }
}