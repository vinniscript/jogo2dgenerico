package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); //Instancia um novo Jframe com todos os seus métodos de mostragem de telas.

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Isso aqui faz com que a janela feche ao clicar no 'x'.

        window.setResizable(false); // Não deixa você diminuir ou aumentar o tamanho da tela, a janela é definitiva.

        window.setTitle("2d aventuras divertidas uhuuuuuu"); // Seta o título da janela (Geralmente o nome do jogo).

        window.setLocationRelativeTo(null); // Faz a tela aparecer no centro.

        window.setVisible(true); // faz  a tela ser visível.

    }

}
