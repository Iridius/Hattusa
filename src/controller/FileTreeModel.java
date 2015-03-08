package controller;

import Alexandria.Library;
import model.ITreeElement;
import model.TreeElementImpl;
import model.TreeElementVoid;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

public class FileTreeModel implements TreeModel {
    private Path _root;
    private Collection<ITreeElement> _elements;
    private Hashtable <Path, Collection> _parents;

    public FileTreeModel(Path rootFolder) {
        _root = rootFolder;
        _elements = getElements(_root, new TreeElementVoid(_root.toString()));
    }

    @Override
    public Object getRoot() {
        return _root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        int i = 0;
        for(ITreeElement element:  getChildren((Path)parent)) {
            if(i == index) {
                return Paths.get(element.getName());
            }
            i++;
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        return getChildren((Path)parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
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

    private ITreeElement getElementByPath(Path path) {
        String name = path.toString();

        for(ITreeElement element: _elements) {
            if(element.getName().equals(name) || element.getFullName().equals(name)) {
                return element;
            }
        }

        return null;
    }

    private Collection<ITreeElement> getChildren(Path path) {
        Collection<ITreeElement> directories = new ArrayList<ITreeElement>();
        Collection<ITreeElement> files = new ArrayList<ITreeElement>();

        ITreeElement element = getElementByPath(path);
        //if(element == null) {
        //    return directories;
        //}

        for(ITreeElement child: _elements){
            ITreeElement parent = child.getParent();

            if(parent != null && parent.equals(element)) {
                if(Library.isFolder(Paths.get(child.getFullName()))) {
                    directories.add(child);
                } else {
                    files.add(child);
                }
            }
        }

        directories.addAll(files);
        return directories;
    }

    private Collection<ITreeElement> getElements(Path root, ITreeElement parent) {
        Collection<Path> paths = Library.getFiles(root, false);
        Collection<ITreeElement> result = new ArrayList<ITreeElement>();

        if(root.equals(_root)) {
            result.add(parent);
        }

        for(Path path: paths) {
            String fullName = path.toString();
            String nodeName = fullName.replace(_root.toString() + "\\", "");
            TreeElementImpl current = new TreeElementImpl(nodeName, fullName, parent);

            result.add(current);

            if(Library.isFolder(path)) {
                result.addAll(getElements(path, current));
            }
        }

        return result;
    }
}
