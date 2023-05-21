package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { // Essa classe aqui é para lidar com os inputs do usuário. Scan vai cantar
    // Lembrando que sempre que você implementa uma classe, você tem que tratar de todos os métodos dela, que no caso da KeyListener são esses 3:

    public boolean upPressed, downPressed, leftPressed, rightPressed; // Booleans que interpretam qual sentido o Player quer ir

    @Override
    public void keyTyped(KeyEvent e) {
        // Não é comum usarem esse carinha aqui, usam apenas os 2 abaixo.
    }

    @Override
    public void keyPressed(KeyEvent e) { // Aqui se referencia a tecla que foi pressionada ou segurada.
        int code = e.getKeyCode(); // Retorna um inteiro com o código da tecla pressionada neste evento. Existe uma lista com cada código e seus respectivos valores.

        if (code == KeyEvent.VK_W) { // Se a tecla pressionada foi 'w'
            upPressed = true; // Quer ir para cima
        }
        if (code == KeyEvent.VK_S) { // Se a tecla pressionada foi 's'
            downPressed = true; // Quer ir para baixo
        }
        if (code == KeyEvent.VK_A) { // Se a tecla pressionada foi 'a'
            leftPressed = true; // Quer ir para esquerda
        }
        if (code == KeyEvent.VK_D) { // Se a tecla pressionada foi 'd'
            rightPressed = true; // Quer ir para direita
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // Quase certeza que isso daqui é tipo "Tecla foi solta", se deixou de ser pressionada, então ela sofreu um release
        int code = e.getKeyCode(); // Copia tudo de cima

        if (code == KeyEvent.VK_W) {
            upPressed = false; // Soltou o w, então o boneco precisa parar de subir
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false; // Parou de descer
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false; // Parou de ir à esquerda
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false; // Parou de ir à direita
        }
    } // keyListener é uma interface que trata dos inputs fornecidos pelo teclado do jogador.
}
