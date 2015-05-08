package view.controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.logging.Logger;

public class HLabel extends JLabel {
    private static Logger log = Logger.getLogger(HLabel.class.getName());
    private IRunnable _command;

    private final static Font LABEL_FONT = new Font("Arial", Font.ROMAN_BASELINE, 10);
    private final static Color LABEL_COLOR = Color.BLUE;

    public HLabel(String text) {
        getGUI(text, null);
    }

    public HLabel(String text, IRunnable form) {
        getGUI(text, form);
    }

    private void getGUI(String text, IRunnable command) {
        _command = command;

        this.setText(text);
        this.setFont(LABEL_FONT);
        this.setForeground(LABEL_COLOR);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(_command != null) {
                    _command.run();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Map attributes = LABEL_FONT.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                e.getComponent().setFont(LABEL_FONT.deriveFont(attributes));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setFont(LABEL_FONT);
            }
        });
    }
}
