package view.filetree;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class TemplatesView {
    private static Logger log = Logger.getLogger(TemplatesView.class.getName());

    public TemplatesView() {
        iniGUI();
    }

    private void iniGUI() {
        JFrame form = new JFrame("Структура файла");
        form.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        form.setMinimumSize(new Dimension(500, 400));
        form.setPreferredSize(new Dimension(500, 400));
        form.setMaximumSize(new Dimension(500, 400));
        form.setType(Window.Type.UTILITY);
        //form.setLayout(new FlowLayout(FlowLayout.LEFT));
        form.setLayout(new GridBagLayout());

        //final JPanel content = new JPanel(new GridBagLayout());

        JTextArea text = new JTextArea();

        JLabel refresh = new JLabel("Обновить");
        JLabel add = new JLabel("Добавить...");
        JLabel edit = new JLabel("Изменить...");
        JLabel delete = new JLabel("Удалить");
        JButton btnOk = new JButton("Готово");
        JButton btnCancel = new JButton("Отмена");

        form.add(text, new GridBagConstraints(0, 0, 6, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        form.add(refresh, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        form.add(add, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        form.add(edit, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        form.add(delete, new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        //content.add(text, new GridBagConstraints(0,0,1,2,0,0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5,5,5,5), 0, 0));

        form.add(btnOk, new GridBagConstraints(4, 1, 1, 1, 1, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        form.add(btnCancel, new GridBagConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        //form.getContentPane().add(content, BorderLayout.CENTER);
        //form.getContentPane().add(chkOrientation, BorderLayout.SOUTH);
        form.pack();
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }
}
