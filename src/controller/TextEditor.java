package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor {
    private String _value;
    private JDialog _frame;
    private JTextArea _text;

    public TextEditor(String caption) {
        _frame = new JDialog(null, caption, Dialog.ModalityType.APPLICATION_MODAL);
        _frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _frame.setMinimumSize(new Dimension(500, 400));
        _frame.setPreferredSize(new Dimension(500, 400));
        _frame.setMaximumSize(new Dimension(500, 400));
        _frame.setLayout(new GridBagLayout());

        _text = new JTextArea();
        _text.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JButton btnOk = new JButton("Готово");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _value = _text.getText();
            }
        });

        JButton btnCancel = new JButton("Отмена");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _frame.dispose();
            }
        });

        _frame.add(_text, new GridBagConstraints(0, 0, 3, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        _frame.add(btnOk, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        _frame.add(btnCancel, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }

    public String getValue() {
        return _value;
    }
}
