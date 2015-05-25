package view;

import view.controls.IRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class ScriptView1 implements IRunnable {
    private static Logger log = Logger.getLogger(TemplatesView.class.getName());
    private JDialog _frame;

    @Override
    public void run() {
        getGUI();
    }

    private void getGUI() {
        _frame = new JDialog(null, "Выберите шаблон для анализа файла", Dialog.ModalityType.APPLICATION_MODAL);
        _frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _frame.setMinimumSize(new Dimension(500, 400));
        _frame.setPreferredSize(new Dimension(500, 400));
        _frame.setMaximumSize(new Dimension(500, 400));
        _frame.setLayout(new GridBagLayout());

        JPanel controls = new JPanel();
        controls.setBackground(Color.GREEN);
        controls.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JPanel text = new JPanel();
        text.setBackground(Color.PINK);
        text.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        //text.setPreferredSize(new Dimension(400, 300));

        JTabbedPane attributes = new JTabbedPane();
        attributes.addTab("Конструктор", controls);
        attributes.addTab("В виде текста", text);
        //attributes.setLayout(new GridLayout());

        JButton btnOk = new JButton("Готово");
        JButton btnCancel = new JButton("Отмена");

        _frame.add(attributes, new GridBagConstraints(0, 0, 3, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        _frame.add(btnOk, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        _frame.add(btnCancel, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        _frame.add(attributes);
        _frame.setVisible(true);
    }
}
