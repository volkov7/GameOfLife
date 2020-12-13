package com.project;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        SwingUtilities.invokeLater(window);
    }
}
