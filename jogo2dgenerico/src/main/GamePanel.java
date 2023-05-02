package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel { //GamePanel é uma subclasse que nele contém todos os módulos que o Jpanel tem.
    //Funciona como a tela do jogo.

    // SCREEN SETTINGS
    final int originalTitleSize = 16; // 16x16 tile <- tile são aqueles quadradinhos que designers usam para fazer sprites de cenários e gráficos num geral, como um apoio.
                                        // essa váriavel é para definir o tamanho padrão de cada sprite, seja ele o player, NCPCs, cenários e afins; 16x16 pixels é o tamaho padrão para muitos jogos 2d retro.
    final int scale = 3; // por conta de 16x16 ser muito pouco nos monitores full HD convencionais, vamos ter que escalonar a resolução dos nossos sprites para que o jogo não fique tão minusculo na tela.
                        // usaremos essa variável para multiplicar o tamanho dela. No nosso caso, os sprites vão ficar 16x16 * 3 = 48x48.
    final int tileSize = originalTitleSize * scale; // Aqui é feito o 'scaling' dito acima.

    // Agora vamos ao tamanho da tela que queremos para o nosso jogo. Para isso, precisamos saber quantos tiles queremos que o jogo mostre, tanto vertical, quanto horizontalmente.

    final int maxScreenCol = 16; // 16 tiles (quadradinhos para sprites) na horizontal.
    final int maxScreenRow = 12; // 12 tiles (quadradinhos para sprites) na vertical, fazendo o ratio ser 4 x 3. Fique a vontade para mudar a quantidade de tiles.
    final int screenWidth = tileSize * maxScreenCol; // já que cada tile são 48 pixels (após o upscaling), faremos o calculo de pixels que o jogo terá de tela na horizontal. (48 * 16 = 768p)
    final int screenHeight = tileSize * maxScreenRow; // Aqui fazemos o mesmo, mas com o calculo de pixels da vertical. (48 * 12 = 576p)

    // Agora vamos criar o construtor desse painel do jogo.

    public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Cria de fato a resolução da classe (que é a janela do jogo).
            this.setBackground(Color.black);
            this.setDoubleBuffered(true); // Isso daqui é feito para evitar flickering (Sprites piscando)
    }

}
