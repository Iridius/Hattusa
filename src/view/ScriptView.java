package view;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;

public class ScriptView {
    private static Logger log = Logger.getLogger(ScriptView.class.getName());

    public ScriptView() {
        initGUI();
    }

    private static void initGUI() {
        setDefaultLookAndFeelDecorated(true);
        JFrame mainForm = new JFrame("Структура файла");
        mainForm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainForm.setPreferredSize(new Dimension(400, 300));

//        TreeModel tree = new FileTreeModel(Paths.get("C:\\projects\\Tabularium"));
//        JTree files = new JTree(tree);
//        files.addMouseListener(new FileTreeMouseListener());
//
//        JScrollPane site = new JScrollPane();
//        site.setBackground(Color.DARK_GRAY);
//        site.setMinimumSize(new Dimension(400, 200));
//        site.setPreferredSize(new Dimension(400, 200));
//        site.getViewport().add(files);
//
//        JScrollPane browser = new JScrollPane();
//        browser.setBackground(Color.LIGHT_GRAY);
//        browser.setMinimumSize(new Dimension(200, 200));
//        browser.setPreferredSize(new Dimension(400, 200));
//
//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, site, browser);
//        splitPane.setOneTouchExpandable(true);
//        splitPane.setDividerLocation(400);
//        splitPane.setPreferredSize(new Dimension(800, 600));
//
//        JPanel siteTab = new JPanel();
//        siteTab.add(splitPane);
//
//        _tabbedPane = new JTabbedPane();
//        _tabbedPane.add("Сайт", siteTab);

        //mainForm.add(splitPane, BorderLayout.CENTER);
        mainForm.pack();
        mainForm.setLocationRelativeTo(null);
        mainForm.setVisible(true);
    }
}
