package com.kuang.snake;

import java.net.URL;

import javax.swing.*;

public class Data {
    public static URL headerUrl = Data.class.getResource("./images/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);

    public static URL bodyUrl = Data.class.getResource("./images/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);

    public static URL foodUrl = Data.class.getResource("./images/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);

    public static URL upUrl = Data.class.getResource("./images/up.png");
    public static ImageIcon up = new ImageIcon(upUrl);

    public static URL downUrl = Data.class.getResource("./images/down.png");
    public static ImageIcon down = new ImageIcon(downUrl);

    public static URL leftUrl = Data.class.getResource("./images/left.png");
    public static ImageIcon left = new ImageIcon(leftUrl);

    public static URL rightUrl = Data.class.getResource("./images/right.png");
    public static ImageIcon right = new ImageIcon(rightUrl);
}
