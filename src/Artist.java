import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Artist extends JFrame {
    static final int WIDTH = 1200;
    static final int HEIGHT = 1000;
    static final int SQUARE = Math.min(WIDTH, HEIGHT);
    static final int W_BORDER = (int) (Math.max(WIDTH - HEIGHT, 0) / 2.0);
    static final int H_BORDER = (int) (Math.max(HEIGHT - WIDTH, 0) / 2.0);

    BufferedImage b = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    Graphics g = b.createGraphics();
    Solver solver;

    public Artist(Solver solver) {
        this.solver = solver;
        setVisible(true);
        setSize(WIDTH, HEIGHT);
    }

    public static int getX(double p) {
        return W_BORDER + (int) (p * SQUARE);
    }

    public static int getY(double p) {
        return H_BORDER + (int) (p * SQUARE);
    }

    public static int scale(double p) {
        return (int) (p * SQUARE);
    }

    public void draw_game() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        double size = solver.size;
        int cell = scale(1 / size);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                double px = x / size;
                double py = y / size;
                Color c = switch (solver.cell_at(x, y)) {
                    case NO -> Color.WHITE;
                    case YES -> Color.BLACK;
                    default -> Color.GRAY;
                };
                g.setColor(c);
                g.fillRect(getX(px), getY(py), cell, cell);
            }
        }
        repaint();
    }

    public void paint(Graphics f) {
        f.drawImage(b, 0, 0, null);
    }
}
