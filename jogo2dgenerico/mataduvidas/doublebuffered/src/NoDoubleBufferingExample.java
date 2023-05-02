import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Sem o DoubleBuffering:

public class NoDoubleBufferingExample extends JPanel implements Runnable {

    private int x = 0;
    private int y = 100;
    private int direction = 1;

    public NoDoubleBufferingExample() {
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
            x += direction;
            if (x > getWidth() - 50 || x < 0) {
                direction *= -1;
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("No Double Buffering Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setContentPane(new NoDoubleBufferingExample());
        frame.setVisible(true);
    }

}

