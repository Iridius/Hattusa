package view;

import view.controls.HTextBox;
import view.controls.IForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class ScriptView implements IForm {
    private static Logger log = Logger.getLogger(ScriptView.class.getName());
    private JDialog _form;
    private HTextBox _currentPage;
    private HTextBox _breadcrumbs;
    private HTextBox _mainMenu;
    private HTextBox _mainTemplate;
    private HTextBox _name;
    private HTextBox _content;
    private HTextBox _filter;
    private HTextBox _sort;
    private HTextBox _template;

    @Override
    public void getGUI() {
        _form = new JDialog(null, "Структура файла", Dialog.ModalityType.APPLICATION_MODAL);
        _form.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _form.setMinimumSize(new Dimension(350, 400));
        _form.setPreferredSize(new Dimension(350, 400));
        _form.setMaximumSize(new Dimension(350, 400));
        //_form.setType(Window.Type.UTILITY);
        _form.setLayout(new FlowLayout(FlowLayout.LEFT));

        JCheckBox output = new JCheckBox("output");
        output.setSelected(true);
        output.setPreferredSize(new Dimension(330,21));
        output.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checked = ((JCheckBox) e.getSource()).isSelected();

                _breadcrumbs.setEnabled(checked);
                _currentPage.setEnabled(checked);
                _mainMenu.setEnabled(checked);
            }
        });
        _form.add(output);

        _currentPage = new HTextBox("@CurrentPage");
        _form.add(_currentPage);

        _breadcrumbs = new HTextBox("@Breadcrumbs");
        _form.add(_breadcrumbs);

        _mainMenu = new HTextBox("@MainMenu");
        _form.add(_mainMenu);

        _mainTemplate = new HTextBox("@MainTemplate");
        _form.add(_mainTemplate);

        _name = new HTextBox("@Name", false);
        _form.add(_name);

        _content = new HTextBox("@Content");
        _form.add(_content);

        _filter = new HTextBox("filter", false);
        _form.add(_filter);

        _sort = new HTextBox("sort", false);
        _form.add(_sort);

        _template = new HTextBox("template");
        _form.add(_template);

        _form.pack();
        _form.setLocationRelativeTo(null);
        _form.setVisible(true);
    }
}
