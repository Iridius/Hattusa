package controller;

import view.filetree.FileTreeMouseListener;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFramework {
    private static final Path _source =  Alexandria.TestFramework.getPath(Alexandria.TestFramework.Folders.SOURCE);

    public static Path getSourcePath() {
        return _source;
    }

    public static Path getLiteraturePath() {
        return Paths.get(getSourcePath().toString(), "Literature.html");
    }

    private static TreeModel getModel() {
        FileTreeModel model = new FileTreeModel();
        model.init(_source);
        //return new FileTreeModel(getSourcePath());

        return model;
    }

    public static JTree getTree() {
        JTree tree = new JTree(getModel());
        tree.addMouseListener(new FileTreeMouseListener());

        return tree;
    }
}
