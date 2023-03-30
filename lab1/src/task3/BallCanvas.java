package task3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    public JLabel label;
    private int goneCounter = 0;

    private void incrementGone(int count) {
        goneCounter += count;
        label.setText(goneCounter + " balls");
    }

    public void add(Ball b) {
        this.balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        incrementGone((int)balls.stream().filter(Ball::isGone).count());
        balls.removeIf(Ball::isGone);
        for(int i = 0; i < balls.size(); i++){
            Ball b = balls.get(i);
            b.draw(g2);
        }
    }
}
