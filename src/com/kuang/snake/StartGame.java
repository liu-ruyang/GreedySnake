package com.kuang.snake;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        jFrame.setBounds(30, 30, 900, 720);
        jFrame.setResizable(false);

        jFrame.add(new GamePanel());

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
