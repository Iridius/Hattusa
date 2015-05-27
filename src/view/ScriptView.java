package view;

import Alexandria.Library;
import controller.Blanks;
import controller.TextEditor;
import view.controls.IRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static javax.swing.BorderFactory.*;

public class ScriptView implements IRunnable {
    private static Logger log = Logger.getLogger(TemplatesView.class.getName());
    private Blanks _blanks;
    private JDialog _frame;
    private JTextArea _text;

    public ScriptView(Blanks blanks) {
        _blanks = blanks;
    }

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
        controls.setBorder(createLineBorder(Color.DARK_GRAY));

        //region text editor
        _text = new JTextArea();
        _text.setBorder(createLineBorder(Color.DARK_GRAY));
        setCode();
        //endregion

        //region xml viewer

        //endregion

        JTabbedPane attributes = new JTabbedPane();
        attributes.addTab("Конструктор", controls);
        attributes.addTab("В виде текста", _text);

        //region control buttons: Ok, Cancel
        JButton btnOk = new JButton("Готово");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Path path = getPath();
                if(path == null) {
                    return;
                }

                try {
                    Library.setContent(path, _text.getText());
                } catch (IOException e1) {
                    log.severe("Failed to write file " + path.toString() + ".");
                } finally {
                    _frame.dispose();
                }
            }
        });

        JButton btnCancel = new JButton("Отмена");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _frame.dispose();
            }
        });
        //endregion

        _frame.add(attributes, new GridBagConstraints(0, 0, 3, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        _frame.add(btnOk, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        _frame.add(btnCancel, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }

    private Path getPath() {
        Path path = _blanks.getValue();

        if(path == null) {
            TextEditor editor = new TextEditor("Укажите имя файла");
            if(editor.getValue() != null) {
               path = Paths.get(editor.getValue());
            }
        }

        return path;
    }

    private void setCode() {
        final Path path = _blanks.getValue();
        if(path == null) {
            return;
        }

        String content = Library.getContent(path);
        _text.setText(content);
    }
}
