package view.filetree;


import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTreeMouseListener implements MouseListener {
    private Path _selectedPath;
    public Path getSelectedPath() {
        return _selectedPath;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        JTree source = (JTree)me.getSource();
        TreePath currentPath = source.getPathForLocation(me.getX(), me.getY());

        System.out.println(me.toString());

        if (currentPath != null) {
            source.setSelectionPath(currentPath);
            _selectedPath = preparePath(currentPath);
        }

        //if (me.isPopupTrigger())
        //    doPop(me);
        //TreePath tp = ((JTree)(me.getSource())).getPathForLocation(me.getX(), me.getY());
        //if (tp != null)
        //jtf.setText(tp.toString());
        //    System.out.println(tp.toString());
        //else
        //    System.out.println("");
    }

    private Path preparePath(TreePath path) {
        String fileName = path.toString();
        fileName = fileName.replace("[", "");
        fileName = fileName.replace("]", "");
        fileName = fileName.replace(", ", "\\");

        return Paths.get(fileName);
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
