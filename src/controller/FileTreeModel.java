package controller;

import Alexandria.Library;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FileTreeModel implements TreeModel {
    private TreeElement _root;
    private HashMap<TreeElement, Collection<TreeElement>> _elements;

    private class TreeElement {
        private Path _path;

        public Path getElementPath() {
            return _path;
        }
        public boolean isFolder() {
            return !Library.isFile(_path);
        }

        TreeElement(Path path) {
            _path = path;
        }

        @Override
        public String toString() {
            if(_root != null && !_path.equals(_root.getElementPath())) {
                return _path.getFileName().toString();
            }

            return _path.toString();
        }
    }

    public FileTreeModel(Path rootFolder) {
        _root = new TreeElement(rootFolder);
        _elements = getElements(_root);
    }

    @Override
    public Object getRoot() {
        return _root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        int i = 0;
        for(TreeElement path: _elements.get(parent)) {
            if(i == index) return path;
            i++;
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if(!_elements.containsKey(parent)) {
            return 0;
        }

        return _elements.get(parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return !_elements.keySet().contains(node);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Collection<TreeElement> children = _elements.get(parent);
        int i = 0;
        for(TreeElement path: children) {
            if(path.equals(child)) {
                return i;
            }
            i++;
        }

        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }

    private HashMap<TreeElement, Collection<TreeElement>> getElements(TreeElement root) {
        HashMap<TreeElement, Collection<TreeElement>> result = new HashMap<TreeElement, Collection<TreeElement>>();

        Collection<Path> children = Library.getFiles(root.getElementPath(), false);
        Collection<TreeElement> directories = new ArrayList<TreeElement>();
        Collection<TreeElement> files = new ArrayList<TreeElement>();

        for(Path path: children) {
            TreeElement element = new TreeElement(path);
            if(element.isFolder()) {
                directories.add(element);
            } else {
                files.add(element);
            }
        }

        Collection<TreeElement> elements = new ArrayList<TreeElement>();
        elements.addAll(directories);
        elements.addAll(files);

        if(elements.size() != 0) {
            result.put(root, elements);
        }

        for(TreeElement child: directories) {
            if(child.isFolder()) {
                result.putAll(getElements(child));
            }
        }

        return result;
    }
}
