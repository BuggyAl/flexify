package me.buggyal;

import javax.swing.*;

public class WorkoutApp {

    private JPanel panel1;
    private JComboBox comboBox1;

    public void open() {
        JFrame frame = new JFrame("Workout App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(panel1);
        frame.setVisible(true);
    }

}
