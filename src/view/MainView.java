package view;

import controller.FileTreeModel;
import view.filetree.FileTreeMouseListener;
import view.filetree.MainMenu;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.*;

public class MainView {
    private static JTabbedPane _tabbedPane;
    public static JTree files;

    public static void createGUI() {
        JFrame mainForm = new JFrame("Hattusa");
        mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainForm.setPreferredSize(new Dimension(800, 600));

        /**/
        MainMenu mainMenu = new MainMenu();
        mainForm.setJMenuBar(mainMenu);

        /**/
        TreeModel projectModel = new FileTreeModel();
        files = new JTree(projectModel);
        files.addMouseListener(new FileTreeMouseListener());

        JScrollPane site = new JScrollPane();
        site.setBackground(Color.DARK_GRAY);
        site.setMinimumSize(new Dimension(400, 200));
        site.setPreferredSize(new Dimension(400, 200));
        site.getViewport().add(files);

        JScrollPane browser = new JScrollPane();
        browser.setBackground(Color.LIGHT_GRAY);
        browser.setMinimumSize(new Dimension(200, 200));
        browser.setPreferredSize(new Dimension(400, 200));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, site, browser);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(400);
        splitPane.setPreferredSize(new Dimension(800, 600));

        JPanel siteTab = new JPanel();
        siteTab.add(splitPane);

        _tabbedPane = new JTabbedPane();
        _tabbedPane.add("Сайт", siteTab);

        mainForm.add(splitPane, BorderLayout.CENTER);
        mainForm.pack();
        mainForm.setLocationRelativeTo(null);
        mainForm.setVisible(true);
    }
}