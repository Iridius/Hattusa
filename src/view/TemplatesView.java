package view;

import view.controls.HLabel;
import view.controls.IForm;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class TemplatesView implements IForm {
    private static Logger log = Logger.getLogger(TemplatesView.class.getName());

    @Override
    public void getGUI() {
        JFrame form = new JFrame("Структура файла");
        form.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        form.setMinimumSize(new Dimension(500, 400));
        form.setPreferredSize(new Dimension(500, 400));
        form.setMaximumSize(new Dimension(500, 400));
        form.setType(Window.Type.UTILITY);
        form.setLayout(new GridBagLayout());

        JList text = new JList();
        text.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        HLabel refresh = new HLabel("Обновить");
        HLabel add = new HLabel("Добавить...", new ScriptView());

        HLabel edit = new HLabel("Изменить...");
        HLabel delete = new HLabel("Удалить");
        JButton btnOk = new JButton("Готово");
        JButton btnCancel = new JButton("Отмена");

        form.add(text, new GridBagConstraints(0, 0, 6, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        form.add(refresh, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        form.add(add, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        form.add(edit, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        form.add(delete, new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));

        form.add(btnOk, new GridBagConstraints(4, 1, 1, 1, 1, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        form.add(btnCancel, new GridBagConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        form.pack();
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }
}
