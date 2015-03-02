package view;

import Alexandria.Library;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class MainView {
    private static JTabbedPane _tabbedPane;

    public static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame mainForm = new JFrame("Hattusa");
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mainForm.setPreferredSize(new Dimension(800, 600));

        //JTree files = new JTree(addNodes(null, new File("C:\\projects\\Tabularium")));
        JTree files = new JTree(getNodes("C:\\projects\\Tabularium"));

//        files.addTreeSelectionListener(new TreeSelectionListener() {
//            public void valueChanged(TreeSelectionEvent e) {
//                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
//                        .getPath().getLastPathComponent();
//                System.out.println("You selected " + node);
//            }
//        });



        JScrollPane site = new JScrollPane();
        site.setBackground(Color.DARK_GRAY);
        site.setMinimumSize(new Dimension(200, 200));
        site.setPreferredSize(new Dimension(400, 200));
        site.getViewport().add(files);

        JScrollPane browser = new JScrollPane();
        browser.setBackground(Color.LIGHT_GRAY);
        browser.setMinimumSize(new Dimension(200, 200));
        browser.setPreferredSize(new Dimension(400, 200));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, site, browser);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        splitPane.setPreferredSize(new Dimension(800, 600));

        JPanel siteTab = new JPanel();
        siteTab.add(splitPane);

        _tabbedPane = new JTabbedPane();
        _tabbedPane.add("Сайт", siteTab);

        //mainForm.getContentPane().add(_tabbedPane);
        //mainForm.add(_tabbedPane, BorderLayout.CENTER);
        mainForm.add(splitPane, BorderLayout.CENTER);
        mainForm.pack();
        mainForm.setVisible(true);
    }

//    static DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
//        String curPath = dir.getPath();
//        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
//        if (curTop != null) { // should only be null at root
//            curTop.add(curDir);
//        }
//        Vector ol = new Vector();
//        String[] tmp = dir.list();
//        for (int i = 0; i < tmp.length; i++)
//            ol.addElement(tmp[i]);
//        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
//        File f;
//        Vector files = new Vector();
//        // Make two passes, one for Dirs and one for Files. This is #1.
//        for (int i = 0; i < ol.size(); i++) {
//            String thisObject = (String) ol.elementAt(i);
//            String newPath;
//            if (curPath.equals("."))
//                newPath = thisObject;
//            else
//                newPath = curPath + File.separator + thisObject;
//            if ((f = new File(newPath)).isDirectory())
//                addNodes(curDir, f);
//            else
//                files.addElement(thisObject);
//        }
//        // Pass two: for files.
//        for (int fnum = 0; fnum < files.size(); fnum++)
//            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
//        return curDir;
//    }

        static DefaultMutableTreeNode getNodes(final String rootFolder) {
            Collection<Path> files = Library.getFiles(Paths.get(rootFolder));
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode();

            for(Path file: files) {
                treeNode.add(new DefaultMutableTreeNode(file.toString()));
            }

            return treeNode;
        /*
            String curPath = rootFolder.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
        if (rootNode != null) { // should only be null at root
            rootNode.add(curDir);
        }
        Vector ol = new Vector();
        String[] tmp = rootFolder.list();
        for (int i = 0; i < tmp.length; i++)
            ol.addElement(tmp[i]);
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
        File f;
        Vector files = new Vector();
        // Make two passes, one for Dirs and one for Files. This is #1.
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = (String) ol.elementAt(i);
            String newPath;
            if (curPath.equals("."))
                newPath = thisObject;
            else
                newPath = curPath + File.separator + thisObject;
            if ((f = new File(newPath)).isDirectory())
                getNodes(curDir, f);
            else
                files.addElement(thisObject);
        }
        // Pass two: for files.
        for (int fnum = 0; fnum < files.size(); fnum++)
            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
        return curDir;
        */
    }
}