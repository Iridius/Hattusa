package controller;

import Alexandria.Library;
import model.ITreeElement;
import model.TreeElementImpl;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class FileTreeModel implements TreeModel {
    private Path _root;
    private Collection<ITreeElement> _elements;

    public FileTreeModel(String rootFolder) {
        _root = Paths.get(rootFolder);
        _elements = getElements(_root, null);
    }

    private Collection<ITreeElement> getChildren(ITreeElement parent) {
        Collection<ITreeElement> result = new ArrayList<ITreeElement>();

        for(ITreeElement element: _elements){
            if(element.getParent().equals(parent)){
                result.add(element);
            }
        }

        return result;
    }

    private Collection<ITreeElement> getElements(Path root, ITreeElement parent) {
        Collection<Path> paths = Library.getFiles(root, false);
        Collection<ITreeElement> result = new ArrayList<ITreeElement>();

        for(Path path: paths) {
            String nodeName = path.toString().replace(_root.toString(), "");
            TreeElementImpl current = new TreeElementImpl(nodeName, parent);

            result.add(current);
            result.addAll(getElements(path, current));
        }

        return result;
    }

    @Override
    public Object getRoot() {
        return _root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        int i = 0;
        for(ITreeElement element:  getChildren((ITreeElement)parent)) {
            if(i == index) {
                return element;
            }
            i++;
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        return getChildren((ITreeElement)parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return !((ITreeElement)node).isRoot();
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
