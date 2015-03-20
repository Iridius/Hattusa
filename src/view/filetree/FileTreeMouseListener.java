package view.filetree;


import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTreeMouseListener implements MouseListener {
    private static Logger log = Logger.getLogger(FileTreeMouseListener.class.getName());
    private Path _selectedPath;
    public Path getSelectedPath() {
        return _selectedPath;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTree source = (JTree)e.getSource();
        int posX = e.getX();
        int posY = e.getY();

        TreePath currentPath = source.getPathForLocation(posX, posY);

        if (currentPath != null) {
            setSelection(source, currentPath);
            setSelectedPath(currentPath);
            showMenu(source, posX, posY);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
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

    private void showMenu(JTree source, int posX, int posY) {
        ContextMenu menu = new ContextMenu();
        try{
            menu.show(source, posX, posY);
        } catch(IllegalComponentStateException exc) {
            log.error("Attempt to show invisible menu. Maybe tests.");
        }
    }

    private void setSelection(JTree source, TreePath currentPath) {
        source.setSelectionPath(currentPath);
    }

    private void setSelectedPath(TreePath path) {
        String fileName = path.toString();
        fileName = fileName.replace("[", "");
        fileName = fileName.replace("]", "");
        fileName = fileName.replace(", ", "\\");

        _selectedPath = Paths.get(fileName);
    }
}
