package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Prank {
    String prompt;
    String msg;

    public Prank(String prompt, String msg) {
        this.prompt = prompt;
        this.msg = msg;
        setupGUI();
    }

    void setupGUI() {
        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel(prompt);
        labelPanel.add(label);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton button1 = new JButton("Yes, I am!");
        JButton button2 = new JButton("No, I am not!");
        buttonPanel.add(button1);
        buttonPanel.add(new JPanel());
        buttonPanel.add(button2);

        final int[] clicks = {50};
        Random random = new Random();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();

                JPanel panel = new JPanel();
                JLabel label = new JLabel(msg);
                panel.add(label);
                panel.setBorder(new EmptyBorder(20, 20, 20, 20));

                dialog.setContentPane(panel);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.pack();
                dialog.setVisible(true);

                dialog.addWindowListener(new WindowAdapter() {
                    @Override public void windowClosed(WindowEvent e) {
                        System.exit(0);
                    }
                });
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                JPanel panel = new JPanel();
                String text;
                boolean userWon = false;

                if (clicks[0] > 0) {
                    // Decrement remain clicks
                    clicks[0] -= 1;

                    // Make button invisible for a split sec
                    button2.setVisible(false);
                    button2.setVisible(true);

                    text = "Yes, you are! Click again for " + clicks[0] + " times.";
                } else {
                    text = "OK, you won this time.";
                    userWon = true;
                }
                JLabel label = new JLabel(text);
                panel.add(label);
                panel.setBorder(new EmptyBorder(20, 20, 20, 20));

                if (userWon) {
                    dialog.addWindowListener(new WindowAdapter() {
                        @Override public void windowClosed(WindowEvent e) {
                            System.exit(0);
                        }
                    });
                }

                dialog.setContentPane(panel);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button2.setLocation(random.nextInt(buttonPanel.getWidth() - button2.getWidth()), random.nextInt(buttonPanel.getHeight() - button2.getHeight()));
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        mainPanel.add(labelPanel);
        mainPanel.add(buttonPanel);

        JFrame frame = new JFrame("Click the button");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
