package com.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Window implements Runnable {

    private JFrame frame;
    private Box[][] boxes;
    private Random random = new Random();

    @Override
    public void run() {
        initFrame();
        initBoxes();
        initTimer();
    }

    private void initFrame() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(Config.SIZE_OF_CELL * Config.WIDTH, Config.SIZE_OF_CELL * Config.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Game of Life");
    }

    private void initBoxes() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        for (int i = 0; i < Config.WIDTH; i++) {
            for (int j = 0; j < Config.HEIGHT; j++) {
                boxes[i][j] = new Box(i, j);
                frame.add(boxes[i][j]);
            }
        }

        /*
        Берем клетку и заполняем для нее соседей
         */
        for (int i = 0; i < Config.WIDTH; i++) {
            for (int j = 0; j < Config.HEIGHT; j++) {
                for (int si = -1; si <= 1; si++) {
                    for (int sj = -1; sj <= 1; sj++) {
                        if (!(si == 0 && sj == 0)) {
                            boxes[i][j].cell.addNear(boxes
                                [(i + si + Config.WIDTH) % Config.WIDTH]
                                [(j + sj + Config.HEIGHT) % Config.HEIGHT].cell
                            );
                        }
                    }
                }
            }
        }

        for (int i = 0; i < Config.WIDTH; i++) {
            for (int j = 0; j < Config.HEIGHT; j++) {
                boxes[i][j].cell.status = random.nextBoolean() ? Status.LIVE : Status.NONE;
            }
        }
    }

    private void initTimer() {
        TimerListener t = new TimerListener();
        Timer timer = new Timer(Config.SLEEP_MS, t);
        timer.start();
    }

    private class TimerListener implements ActionListener {
        boolean flop = false;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            flop = !flop;
            for (int i = 0; i < Config.WIDTH; i++) {
                for (int j = 0; j < Config.HEIGHT; j++) {
                    if (flop) {
                        boxes[i][j].step1();
                    } else {
                        boxes[i][j].step2();
                    }
                }
            }
        }
    }
}
