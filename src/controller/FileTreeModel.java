package controller;

import model.ITreeElement;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.Collection;

public class FileTreeModel implements TreeModel {
    private String _rootFolder;
    private DefaultMutableTreeNode _root;

    public FileTreeModel(String rootFolder) {
        _rootFolder = rootFolder;
        _root = new DefaultMutableTreeNode(_rootFolder);

        Collection<ITreeElement> elements = getElements(_rootFolder);
    }

    private Collection<ITreeElement> getElements(String rootFolder) {


        return null;
    }

    @Override
    public Object getRoot() {
        return _root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        return false;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
