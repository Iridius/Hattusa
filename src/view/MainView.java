package view;

import controller.FileTreeModel;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.nio.file.Paths;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;

public class MainView {
    private static JTabbedPane _tabbedPane;
    public static JTree tree;

    public static void createGUI() {
        setDefaultLookAndFeelDecorated(true);
        JFrame mainForm = new JFrame("Hattusa");
        mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainForm.setPreferredSize(new Dimension(800, 600));

        TreeModel files = new FileTreeModel(Paths.get("C:\\projects\\Tabularium"));
        tree = new JTree(files);
        tree.addMouseListener(new PopClickListener());

        JScrollPane site = new JScrollPane();
        site.setBackground(Color.DARK_GRAY);
        site.setMinimumSize(new Dimension(400, 200));
        site.setPreferredSize(new Dimension(400, 200));
        site.getViewport().add(tree);

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
        mainForm.setVisible(true);
    }

//    static DefaultMutableTreeNode getNodes(final String rootFolder) {
//        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode();
//
//        Collection<Path> files = Library.getFiles(Paths.get(rootFolder));
//        for(Path path: files) {
//            String fullNodeName = path.toString();
//            String nodeName = fullNodeName.replace(rootFolder, "");
//
//            String parentNodeName = path.getParent().toString();
//
//            DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(nodeName);
//
//            treeNode.add(currentNode);
//            //addChildNodes(files, currentNode, fullNodeName);
//        }
//
//        return treeNode;
//    }

    //private static void addChildNodes(Collection<Path> files, DefaultMutableTreeNode currentNode, String fullNodeName) {
    //}
}