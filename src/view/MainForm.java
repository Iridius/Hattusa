package view;

import javax.swing.*;
import java.awt.*;

public class MainForm {
    private static JTabbedPane _tabbedPane;

    public static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame mainForm = new JFrame("Hattusa");
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainForm.setPreferredSize(new Dimension(400, 300));

        _tabbedPane = new JTabbedPane();
        //JLabel label = new JLabel("Test label");
        //mainForm.getContentPane().add(label);


        mainForm.add(_tabbedPane, BorderLayout.CENTER);
        mainForm.pack();
        mainForm.setVisible(true);
    }

    public static void add(String name) {
        _tabbedPane.addTab(name, new JPanel());
    }
}
