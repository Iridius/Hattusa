package view;

import controller.FileTreeModel;
import model.Config;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class MainMenu extends JMenuBar {
    private static Logger log = Logger.getLogger(MainMenu.class.getName());
    JMenuItem _templates;
    JMenuItem _config;

    public MainMenu() {
        // region File
        /* File */
        JMenu file = new JMenu("Файл");

        /* File/Open */
        JMenuItem open = new JMenuItem("Открыть проект");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                final JFileChooser open = new JFileChooser(Config.getLastProjectPath().toFile());
                open.setDialogTitle("Выберите папку проекта");
                open.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if(open.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                    Path project = Paths.get(open.getSelectedFile().getAbsolutePath());
                    Config.setProjectPath(project);

                    FileTreeModel model = new FileTreeModel(Config.getProjectPath());
                    MainView.files.setModel(model);
                }
            }
        });

        /* File/Exit */
        JMenuItem exit = new JMenuItem("Выход");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(open);
        file.add(exit);
        //endregion
        // region Project
        /* Project */
        JMenu project = new JMenu("Проект");
        project.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                final boolean projectEnabled = Config.getProjectPath() != null;

                _templates.setEnabled(projectEnabled);
                _config.setEnabled(projectEnabled);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        /* Templates */
        _templates = new JMenuItem("Шаблоны...");
        _templates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TemplatesView("").run(TemplatesView.OpenMode.VIEW);
            }
        });

        /* Config */
        _config = new JMenuItem("Конфигурация...");
        _config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfigView().run();
            }
        });

        project.add(_templates);
        project.add(_config);
        //endregion

        this.add(file);
        this.add(project);
    }
}
