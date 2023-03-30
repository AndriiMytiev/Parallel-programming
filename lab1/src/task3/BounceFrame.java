package task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 750;
    public static final int HEIGHT = 550;
    public JLabel label;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program - task3");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStop = new JButton("Stop");
        JButton buttonRace = new JButton("Red Blue Ball race");
        label = new JLabel("0 balls");
        canvas.label = label;


        buttonRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int blueBallsCount = 100;

                ArrayList<BallThread> threads = new ArrayList<>();

                for (int i = 0; i < blueBallsCount; ++i) {
                    Ball blue = new Ball(canvas, Color.BLUE, canvas.getWidth() / 2, canvas.getHeight() / 2);
                    canvas.add(blue);
                    BallThread td = new BallThread(blue, 1);
                    threads.add(td);
                }
                Ball red = new Ball(canvas, Color.RED, canvas.getWidth() / 2, canvas.getHeight() / 2);
                BallThread thread = new BallThread(red, 8);
                threads.add(thread);
                canvas.add(red);

                for (BallThread bt: threads) {
                    bt.start();
                }
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(label);
        buttonPanel.add(buttonRace);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
