package view;

import controller.Blanks;
import view.controls.HLabel;
import view.controls.IRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class TemplatesView implements IRunnable {
    private static Logger log = Logger.getLogger(TemplatesView.class.getName());
    private JDialog _frame;
    private JList _list;
    private Blanks _blanks;

    public enum OpenMode { VIEW, SELECT }

    @Override
    public void run() {
        run(OpenMode.SELECT);
    }

    public void run(OpenMode mode) {
        getGUI(mode);
    }

    private void getGUI(OpenMode mode) {
        _frame = new JDialog(null, "Выберите шаблон для анализа файла", Dialog.ModalityType.APPLICATION_MODAL);
        _frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _frame.setMinimumSize(new Dimension(500, 400));
        _frame.setPreferredSize(new Dimension(500, 400));
        _frame.setMaximumSize(new Dimension(500, 400));
        _frame.setLayout(new GridBagLayout());
        _frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                _blanks.run();
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        _list = new JList();
        _list.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        _blanks = new Blanks(_list);
        HLabel refresh = new HLabel("Обновить", _blanks);
        HLabel add = new HLabel("Добавить...", new ScriptView());

        HLabel edit = new HLabel("Изменить...", new ScriptView());
        HLabel delete = new HLabel("Удалить");

        JButton btnSelect = new JButton("Выбрать");
        btnSelect.setEnabled(mode == OpenMode.SELECT);
        JButton btnCancel = new JButton("Отмена");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _frame.dispose();
            }
        });

        _frame.add(_list, new GridBagConstraints(0, 0, 6, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        _frame.add(refresh, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        _frame.add(add, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        _frame.add(edit, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));
        _frame.add(delete, new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(0, 5, 0, 0), 0, 0));

        _frame.add(btnSelect, new GridBagConstraints(4, 1, 1, 1, 1, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        _frame.add(btnCancel, new GridBagConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }
}
