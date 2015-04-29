package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class ScriptView {
    private static Logger log = Logger.getLogger(ScriptView.class.getName());
    private TextBoxControl _currentPage;
    private TextBoxControl _breadcrumbs;
    private TextBoxControl _mainMenu;
    private TextBoxControl _mainTemplate;
    private TextBoxControl _name;
    private TextBoxControl _content;
    private TextBoxControl _filter;
    private TextBoxControl _sort;
    private TextBoxControl _template;

    public ScriptView() {
        initGUI();
    }

    private void initGUI() {
        JFrame propertiesForm = new JFrame("Структура файла");
        propertiesForm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        propertiesForm.setMinimumSize(new Dimension(350, 400));
        propertiesForm.setPreferredSize(new Dimension(350, 400));
        propertiesForm.setMaximumSize(new Dimension(350, 400));
        propertiesForm.setType(Window.Type.UTILITY);
        propertiesForm.setLayout(new FlowLayout(FlowLayout.LEFT));

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
        propertiesForm.add(output);

        _currentPage = new TextBoxControl("@CurrentPage");
        propertiesForm.add(_currentPage);

        _breadcrumbs = new TextBoxControl("@Breadcrumbs");
        propertiesForm.add(_breadcrumbs);

        _mainMenu = new TextBoxControl("@MainMenu");
        propertiesForm.add(_mainMenu);

        _mainTemplate = new TextBoxControl("@MainTemplate");
        propertiesForm.add(_mainTemplate);

        _name = new TextBoxControl("@Name", false);
        propertiesForm.add(_name);

        _content = new TextBoxControl("@Content");
        propertiesForm.add(_content);

        _filter = new TextBoxControl("filter", false);
        propertiesForm.add(_filter);

        _sort = new TextBoxControl("sort", false);
        propertiesForm.add(_sort);

        _template = new TextBoxControl("template");
        propertiesForm.add(_template);

        propertiesForm.pack();
        propertiesForm.setLocationRelativeTo(null);
        propertiesForm.setVisible(true);
    }
}
