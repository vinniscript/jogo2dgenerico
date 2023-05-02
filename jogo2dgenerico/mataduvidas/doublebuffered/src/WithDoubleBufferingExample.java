import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WithDoubleBufferingExample extends JPanel implements Runnable {

    private int x = 0;
    private int y = 100;
    private int direction = 1;
    private BufferedImage offscreen;

    public WithDoubleBufferingExample() {
        Thread t = new Thread(this);
        t.start();
        this.setDoubleBuffered(true); // Habilita o double buffering
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (offscreen == null) {
            offscreen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        Graphics bufferGraphics = offscreen.getGraphics();
        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());
        bufferGraphics.setColor(Color.RED);
        bufferGraphics.fillOval(x, y, 50, 50);
        g.drawImage(offscreen, 0, 0, null);
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
        JFrame frame = new JFrame("Double Buffering Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setContentPane(new WithDoubleBufferingExample());
        frame.setVisible(true);
    }
}