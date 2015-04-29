package view;

import view.controls.HLabel;
import view.controls.IForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class TemplatesView implements IForm {
    private static Logger log = Logger.getLogger(TemplatesView.class.getName());
    private JFrame _frame;

    @Override
    public void getGUI() {
        _frame = new JFrame("Структура файла");
        _frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _frame.setMinimumSize(new Dimension(500, 400));
        _frame.setPreferredSize(new Dimension(500, 400));
        _frame.setMaximumSize(new Dimension(500, 400));
        _frame.setType(Window.Type.UTILITY);
        _frame.setLayout(new GridBagLayout());

        JList text = new JList();
        text.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        HLabel refresh = new HLabel("Обновить");
        HLabel add = new HLabel("Добавить...", new ScriptView());

        HLabel edit = new HLabel("Изменить...");
        HLabel delete = new HLabel("Удалить");
        JButton btnOk = new JButton("Готово");

        JButton btnCancel = new JButton("Отмена");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _frame.dispose();
            }
        });

        _frame.add(text, new GridBagConstraints(0, 0, 6, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        _frame.add(refresh, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        _frame.add(add, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        _frame.add(edit, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        _frame.add(delete, new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));

        _frame.add(btnOk, new GridBagConstraints(4, 1, 1, 1, 1, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        _frame.add(btnCancel, new GridBagConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        _frame.pack();
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }
}
