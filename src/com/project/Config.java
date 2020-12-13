package com.project;

import java.awt.Color;

public class Config {
    public static final int SIZE_OF_CELL = 10;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 60;
    public static final int SLEEP_MS = 200;

    public static Color getColor(Status status) {
        switch (status) {
            case NONE: return Color.BLACK;
            case BORN: return Color.GRAY;
            case LIVE: return Color.WHITE;
            case DIED: return Color.BLUE;
            default: return Color.MAGENTA;
        }
    }
}
