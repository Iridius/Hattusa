package view.filetree;

import view.MainView;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContextMenu extends JPopupMenu {
    private JMenuItem _miView;
    private JMenuItem _miEdit;
    private JMenuItem _miDelete;
    private JMenuItem _miConstruct;
    private JMenuItem _miDeconstruct;

    public ContextMenu(){
        _miView = new JMenuItem("Просмотр");

        _miEdit = new JMenuItem("Редактировать...");

        _miDelete = new JMenuItem("Удалить");
        _miDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreePath tree_path = MainView.tree.getSelectionPath();
                Path path = preparePath(tree_path);

                //if(JOptionPane.showConfirmDialog(MainView.tree, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                    //Library.delete(path);
                    //MainView.tree.getModel().remove(path);
                //}
            }
        });

        _miConstruct = new JMenuItem("Собрать");
        _miDeconstruct = new JMenuItem("Разобрать...");

        this.add(_miView);
        this.add(_miEdit);
        this.add(_miDelete);
        this.add(new Separator());
        this.add(_miConstruct);
        this.add(_miDeconstruct);
    }

    private Path preparePath(TreePath path) {
        String fileName = path.toString().replace(", ", "\\");
        fileName = fileName.replace("[", "");
        fileName = fileName.replace("]", "");

        return Paths.get(fileName);
    }
}