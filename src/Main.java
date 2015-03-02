import view.MainView;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainView.createGUI();
            }
        });
    }
}

//import java.awt.*;
//import javax.swing.*;
//
//    public class Main extends JPanel {
//    public JSplitPane splitPane;
//
//    public Main() {
//
//
//        JScrollPane listScrollPane = new JScrollPane();
//        listScrollPane.setMinimumSize(new Dimension(100, 50));
//
//        JScrollPane pictureScrollPane = new JScrollPane();
//        pictureScrollPane.setMinimumSize(new Dimension(100, 50));
//
//        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, pictureScrollPane);
//        splitPane.setOneTouchExpandable(true);
//        splitPane.setDividerLocation(150);
//        splitPane.setPreferredSize(new Dimension(400, 200));
//    }
//
//    private static void createAndShowGUI() {
//        JFrame frame = new JFrame("SplitPaneDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        Main splitPaneDemo = new Main();
//        frame.getContentPane().add(splitPaneDemo.splitPane);
//
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//}