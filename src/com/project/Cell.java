package com.project;

import java.util.ArrayList;

public class Cell {
    Status status;
    private ArrayList<Cell> near;

    public Cell() {
        this.status = Status.NONE;
        near = new ArrayList<>();
    }

    public void addNear(Cell cell) {
        near.add(cell);
    }

    public void step1() {
        int around = countNearCells();
        this.status = status.step1(around);
    }

    public void step2() {
        status = status.step2();
    }

    private int countNearCells() {
        int count = 0;
        for (Cell cell : near) {
            if (cell.status.isCell()) count++;
        }
        return count;
    }
}
