package view.controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class HTextBox extends JPanel{
    private static Logger log = Logger.getLogger(HTextBox.class.getName());
    private static final int _width = 330;
    private static final int _alignAxe = 105;

    private JLabel _label;
    private JTextField _text;
    private JButton _button;

    public HTextBox(String caption) {
        initGUI(caption, true);
    }

    public HTextBox(String caption, boolean selectButtonVisible) {
        initGUI(caption, selectButtonVisible);
    }

    private void initGUI(String caption, boolean selectButtonVisible) {
        this.setPreferredSize(new Dimension(_width, 30));

        /* Text caption */
        _label = new JLabel(caption + ":");
        _label.setSize(new Dimension(_alignAxe - 5, 21));
        _label.setPreferredSize(new Dimension(_alignAxe - 5, 21));
        _label.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(_label);

        /* Text field */
        _text = new JTextField();
        _text.setPreferredSize(new Dimension(_width - _alignAxe - 40, 21));
        this.add(_text);

        /* File open button */
        _button = new JButton("...");
        _button.setPreferredSize(new Dimension(21,21));
        _button.setVisible(selectButtonVisible);
        _button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser open = new JFileChooser();
                if(open.showOpenDialog(_button) == JFileChooser.APPROVE_OPTION) {
                    _text.setText(open.getSelectedFile().getName());
                }
            }
        });
        this.add(_button);

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        layout.putConstraint(SpringLayout.WEST, _label, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, _label, 3, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, _text, _alignAxe + 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, _text, 3, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, _button, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, _button, 3, SpringLayout.NORTH, this);
    }

    @Override
    public void setEnabled(boolean state) {
        _label.setEnabled(state);
        _text.setEnabled(state);
        _button.setEnabled(state);
    }
}
