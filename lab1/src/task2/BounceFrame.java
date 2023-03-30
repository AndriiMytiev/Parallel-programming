package task2;

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
        this.setTitle("Bounce program - task2");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Add Ball");
        JButton buttonStop = new JButton("Stop");
        label = new JLabel("0 balls");
        canvas.label = label;

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, canvas.getPockets(), Color.GREEN);
                canvas.add(b);
                BallThread thread = new BallThread(b, 1);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });


        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(label);
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
