package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable { //GamePanel é uma subclasse que nele contém todos os módulos que o Jpanel tem.
    //Funciona como a tela do jogo.

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile <- tile são aqueles quadradinhos que designers usam para fazer sprites de cenários e gráficos num geral, como um apoio.
    // essa váriavel é para definir o tamanho padrão de cada sprite, seja ele o player, NCPCs, cenários e afins; 16x16 pixels é o tamaho padrão para muitos jogos 2d retro.
    final int scale = 3; // por conta de 16x16 ser muito pouco nos monitores full HD convencionais, vamos ter que escalonar a resolução dos nossos sprites para que o jogo não fique tão minusculo na tela.
    // usaremos essa variável para multiplicar o tamanho dela. No nosso caso, os sprites vão ficar 16x16 * 3 = 48x48.
    final int tileSize = originalTileSize * scale; // Aqui é feito o 'scaling' dito acima.

    // Agora vamos ao tamanho da tela que queremos para o nosso jogo. Para isso, precisamos saber quantos tiles queremos que o jogo mostre, tanto vertical, quanto horizontalmente.
    final int maxScreenCol = 16; // 16 tiles (quadradinhos para sprites) na horizontal.
    final int maxScreenRow = 12; // 12 tiles (quadradinhos para sprites) na vertical, fazendo o ratio ser 4 x 3. Fique a vontade para mudar a quantidade de tiles.
    final int screenWidth = tileSize * maxScreenCol; // já que cada tile são 48 pixels (após o upscaling), faremos o calculo de pixels que o jogo terá de tela na horizontal. (48 * 16 = 768p)
    final int screenHeight = tileSize * maxScreenRow; // Aqui fazemos o mesmo, mas com o calculo de pixels da vertical. (48 * 12 = 576p)

    // FPS
    int FPS = 60;


    // Fazendo as chamadas da leitura do teclado do jogador:

    KeyHandler keyH = new KeyHandler();


    // Vamos entrar na lógica de deixar um jogo aberto e os seus ticks (rendeização de frames) por segundo
    Thread gameThread; // Thread é uma classe que, uma vez iniciada, manterá o programa rodando até você o encerrar

    // Alocar posição inicial do jogador
    int playerX = 100;
    int playerY = 100;
    // Posição das coordenadas Horizontais (X) e Verticais (Y) do personagem na tela assim que o jogo começa ou reinicia
    int playerSpeed = 4; // Velocidade do personagem (isso significa os pixels que seu personagem anda)

    // Agora vamos criar o construtor desse painel do jogo

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Cria de fato a resolução da classe (que é a janela do jogo)
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Isso daqui é feito para evitar flickering (Sprites piscando). Ajuda na renderização do games
        this.addKeyListener(keyH); // Adiocinando á tela as ações do player com base nos seus inputs
        this.setFocusable(true); // Isso faz com que o GamePanel posse se focar a receber um input, não entendi direito
    }


    public void startGameThread() {
        gameThread = new Thread(this); // o 'this' está referenciando esse arquivo nosso "GamePanel", e ele está sendo instanciado nessa nova thread construtora
        gameThread.start(); // Aqui ocorre a segmentação da execução do código, aqui o processo do jogo é independente
    }
    //                                                          ###  ----------------- MÉTODO SLEEP ----------------- ###
    /* <- DESCOMENTAR PARA TESTAR
    @Override
    public void run() { // Esse é um método abstrato da nossa implementação da interface Runnable. Ele separa uma linha de processamento para o nosso programa, e aqui a manipulamos
        // Aqui dentro será nosso Game Loop, que será o núcleo do nosso jogo

        double drawInterval = 1000000000 / FPS; //São 9 dígitos 0 (1 bilhão) dividos pelo FPS que decidimos que nosso jogo vai ter = 0.16666... segundos
        double nextDrawTime = System.nanoTime() + drawInterval; // Essa váriavel esta limitando que o próximo desenho na tela etm que ser após o intervalo que calculamos precisamente para bater as 60 atualizações na tela por segundo


        while (gameThread != null) { //Enquanto essa linha existir, faça isso:
            // Se não definirmos um intervalo de atualizações por segundo, o computador vai fazer isso o mais rápido que pode, e tu vai acontecer rapido demais para conseguirmos acompanhar.
            // por exemplo ali embaixo onde fizemos o processo do retângulo se mover, sem isso, um simples pressionar da tecla faria o computador ler a mesma tecla milhares de vezes até seu dedo a soltar.

            long currenTime = System.nanoTime(); // Retorna o atual valor de execução do Java Virtual Machine´s high-resolution time source, só que em nanosegundos. é necessário 1 bilhão de nanosegundos para formar 1 segundo
            //           long currentTime2 = System.currentTimeMillis(); Também podemos usar isso, porém nano é mais preciso.

            // Bora para os FPS. Existem 2 métodos consolidados na criação de jogos em java. Vamos primeiro para o método "Sleep".


//            System.out.println("The game loop is running");
            // 1 UPDATE: Atualizar as informações posicionais do jogador

            // 2 DRAW: Desenhar a informação atualizada na tela

            // FPS Frames Per Second (Frames Por Segundo)

            update(); // Atualiza as informações conforme o desenrolar do jogo.

            repaint(); // Por algum motivo é assim que você chama o método do paintComponent
            // Essas 2 linhas acima é o que fazem o jogo ficar atualizando e redesenhando as atualizações que vão rolando no desenolver da partida do jogador
            // Enquanto o jogo não fechar, atualize, redesenhe... Assim até o fim da execução do código

//                                                          ###  ----------------- MÉTODO SLEEP ----------------- ###


            // essa variavel de cima ta calculando o quanto falta para que bater o nosso intervalo estipulado após ela ter feito o processo do update() e o repaint(). e é esse intervalo que ele irá "dormir" (método sleep)
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // O sleep trabalha apenas com milissegundos, então aqui convertemos os nanonesegundo em milis (dividindo por 1 milhão) para que não tenha problemas na hora de calcular o intervalo.

                if (remainingTime < 0) { // Apenas uma convenção para caso o programa gargale em uma das atualizações e sua execução passe o tempo do intervalo, fazem isso apenas para desconsiderar o intervalo nessa próxima atualização e não ter congelamentos no jogo.
                    remainingTime = 0; // Se o update e o repaint demorar mais que o drawInterval (nosso intervalo), então essa thread não precisa dormir, já que não foi necessário nenhum descanso de uma atualização para outra
                }

                Thread.sleep((long) remainingTime); // tivemos que fazer um casting, pois o sleep só trabalha com váriaveis do tipo long.

                nextDrawTime += drawInterval; //r Redefinindo que após as execuções, a próxima execução tem que ser feita após o intervalo estipulado (0.016666... segundos)

            } catch (InterruptedException e) {
                throw new RuntimeException(e); // por algum motivo o Thread.sleep também exige ser tratado com try catch.
            }
            // Esse bloco de código está fazendo o processo dormir, ou seja, não fazer mais nada até que o intervalo de tempo para novas atualizações e resenhos na tela seja respeitado.

                                                                    ###  ----------------------------------  ###


        }
    }
*/
//                                                          ###  ----------------- MÉTODO DELTA/ACUMULATOR ----------------- ###

    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        // Para mostragem de FPS na tela:
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime(); // Ao começar o loop do jogo, checamos o tempo de execução atual

            delta += (currentTime - lastTime) / drawInterval; // Aqui calculamos quanto tempo se passou, adicionando ao delta os calculos de nanosegundos divididos pela quantidade de FPS que queremos, onde foi feito o calculo (que 60 FPS em nanosegundo é 0.0166...)
            timer += (currentTime - lastTime); // Vai acumular os nanosegundos passados
            lastTime = currentTime; //Aqui atualizamos o valor do antigo "momento atual" para a váriavel "ultimo momento", para salvar o valor e a variável currentTime possa pegar o novo tempo de execução.

            if (delta >= 1) { // Quando aquele o resultado daquele calculo do delta foi maior ou igual a 1, ele:
                update(); // Atualiza a lógica do jogo
                repaint(); // Redesenha na tela
                delta--; // Retornamos o delta para 0, após a atualiazação ter sido realizada

                drawCount++; // Acumulador de desenhos a cada loop (contagem de frames por segundo).
            }
            if (timer >= 1000000000) { // Se o timer bater 1 bilhão de nanosegundos (ou 1 segundo), ele:
                System.out.println("FPS: " + drawCount); // concatena "FPS: " à quantidade de atualizações que nosso programa teve em 1 segundo, cujo valor estava sendo armazenado nessa "drawCount"
                drawCount = 0; // Reseta o drawCount para o próximo segundo
                timer = 0; // Junto do timer
            }
        }
    }

    public void update() { // Aqui vamos trabalhar com as atualizações por segundo da tela do jogo
        if (keyH.upPressed == true) { // Caso o boolean 'upPressed' seja validado
            playerY -= playerSpeed; // Y aumenta o valor se ele for para baixo, como aqui o player esta subindo, o playerY sofre uma subtração da velocidade do personagem
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed; // Personagem desce, Y sobe, por isso a soma.
        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed; // o positivo do X é o personagem ir para direito, então nesse caso ele sofre uma subtração
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed; // Personagem indo para direita, então soma com sua velocidade.
            // Lembrando que as coordenadas são pixels da nossa tela do jogo, então sempre se começa em X:100 e Y:100, ao andar para cima, o jogo atualiza para X:100, Y:104, e nosso personagem sobe 4 pixels.
        }

    }

    public void paintComponent(Graphics g) { // Isso aqui já é um método built-in do java. É um método padrao do JPanel. O usaremos para desenhar
        // Graphics referencia uma classe que tem várias funções para desenhar objetos na tela

        super.paintComponent(g); // O super é para se referenciar à classe pai (que nesse caso é a nossa GamePanel, cujo está estendendo JPanel)
        Graphics2D g2 = (Graphics2D) g; // Estende da classe Graphics para dar um molejo melhor a se trabalhar com geometria, informações de coordenadas, gerência de cores, textos e layouts.

        g2.setColor(Color.white); // Seta a cor do fundo

        g2.fillRect(playerX, playerY, tileSize, tileSize); // Cria um retângulo que será nosso personagem. Usamos o TileSize no lugar de width e height para garantir melhor conversasão de valores com o resto dos sprites

        g2.dispose(); // Descarta aquele contexto de gráfico e libera recursos para o sistema usar. Apenas boas práticas

    }
}
