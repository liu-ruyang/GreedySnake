package com.kuang.snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;

// 游戏的面板
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int snakeX[] = new int[600];
    int snakeY[] = new int[500];
    int length;
    String fx = null;
    boolean isStart;
    boolean isFailed;
    int score;
    int foodx;
    int foody;
    Random random = new Random();

    Timer timer = new Timer(100, this);

    public GamePanel() {
        init();
        // 获得焦点和键盘事件
        setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void init() {
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        fx = "R";
        isStart = false;
        isFailed = false;
        score = 0;
        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);
    }

    //绘制面板

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);   //
        // 绘制静态的面板
        this.setBackground(Color.black);

        Data.header.paintIcon(this, g, 25, 11);
        g.setColor(Color.gray);
        g.fillRect(25, 75, 850, 600);

        // 画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度：" + length, 750, 35);
        g.drawString("分数：" + score, 750, 55);

        // 放上食物
        Data.food.paintIcon(this, g, foodx, foody);
        // 用画笔g将本组件头部放在，this这个容器里
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        // 身体放上去
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }


        // 设置开始键
        if (isStart == false) {
            g.setColor(Color.white);
            setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }

        if (isFailed) {
            g.setColor(Color.RED);
            setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("失败，按空格键重新开始", 250, 300);
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();   // 获取键盘按的哪一个
        if (isStart) {
            // 小蛇移动方向
            if (keyCode == KeyEvent.VK_UP) {
                fx = "U";
            } else if (keyCode == KeyEvent.VK_DOWN) {
                fx = "D";
            } else if (keyCode == KeyEvent.VK_LEFT) {
                fx = "L";
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                fx = "R";
            }
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            // 重新开始
            if (isFailed) {
                init();
            } else { // 暂停或继续
                isStart = !isStart;
//                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isFailed == false) {
            // 吃食物
            if (foodx == snakeX[0] && foody == snakeY[0]) {
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
                length++;
                score += 10;
            }
            // 移动
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            // 走向
            if (fx.equals("R")) {
                snakeX[0] += 25;
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (fx.equals("L")) {
                snakeX[0] -= 25;
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            } else if (fx.equals("U")) {
                snakeY[0] -= 25;
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            } else if (fx.equals("D")) {
                snakeY[0] += 25;
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            }

            // 判断失败，撞到自己，算失败
            for (int i = 1; i < length; i++) {
                if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]) {
                    isFailed = true;
                    break;
                }
            }
        }
        repaint();
    }
}

// 键盘监听器
