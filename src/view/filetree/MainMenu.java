package view.filetree;

import controller.FileTreeModel;
import model.Config;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class MainMenu extends JMenuBar {
    private static Logger log = Logger.getLogger(MainMenu.class.getName());

    public MainMenu() {
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
                    FileTreeModel model = new FileTreeModel(project);
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

        this.add(file);
    }
}
