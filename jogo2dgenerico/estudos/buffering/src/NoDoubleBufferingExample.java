import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NoDoubleBufferingExample extends JPanel implements Runnable {

    private int x = 0;
    private int y = 100;
    private int direction = 1;

    public NoDoubleBufferingExample() {
        // Cria uma nova Thread e inicia a execução do método "run"
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, 50, 50);
    }

    @Override
    public void run() {
        while (true) {
            // Move o objeto na direção indicada
            x += direction;
            // Se chegar no limite da janela, muda a direção
            if (x > getWidth() - 50 || x < 0) {
                direction *= -1;
            }
            // Repinta a tela com as novas coordenadas do objeto
            repaint();
            try {
                // Pausa a Thread por um curto período de tempo para reduzir o consumo de recursos
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

    public static void main(String[] args) {
        // Cria uma nova janela
        JFrame frame = new JFrame("No Double Buffering Example");
        // Define o comportamento padrão ao clicar no botão fechar da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Define o tamanho da janela
        frame.setSize(400, 200);
        // Define o conteúdo da janela como uma instância da classe "NoDoubleBufferingExample"
        frame.setContentPane(new NoDoubleBufferingExample());
        // Torna a janela visível
        frame.setVisible(true);
    }

}
