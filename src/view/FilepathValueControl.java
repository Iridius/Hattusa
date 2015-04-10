package view;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class FilepathValueControl extends JPanel{
    private static Logger log = Logger.getLogger(FilepathValueControl.class.getName());

    public FilepathValueControl(String caption) {
        this.setBackground(Color.PINK);

        JLabel label = new JLabel(caption);
        label.setLocation(5, 5);
        label.setVisible(true);
        this.add(label);

        JTextField text = new JTextField();
        text.setLocation(label.getX() + label.getWidth(), 5);
        text.setSize(21, 100);
        this.add(text);

        this.setVisible(true);
    }

//    @Override
//    public void setLocation(int x, int y) {
//        this.setLocation(x,y);
//    }
//
//    @Override
//    public void setVisible(boolean visible) {
//        this.setVisible(visible);
//    }
}
