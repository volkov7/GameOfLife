package com.project;

import javax.swing.JPanel;
import java.util.Random;

public class Box extends JPanel {
    private int x;
    private int y;
    Cell cell;

    public Box(int x, int y) {
        super();
        this.cell = new Cell();
        setBounds(x * Config.SIZE_OF_CELL, y * Config.SIZE_OF_CELL, Config.SIZE_OF_CELL, Config.SIZE_OF_CELL);
        setBackground(Config.getColor(Status.NONE));
    }

    public void setColor() {
        setBackground(Config.getColor(cell.status));
    }

    public void step1() {
        cell.step1();
        setColor();
    }

    public void step2() {
        cell.step2();
        setColor();
    }
}
