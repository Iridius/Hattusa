package controller;

import view.filetree.FileTreeMouseListener;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFramework {
    public static Path getSourcePath() {
        return Alexandria.TestFramework.getPath(Alexandria.TestFramework.Folders.SOURCE);
    }

    public static Path getLiteraturePath() {
        return Paths.get(getSourcePath().toString(), "Literature.html");
    }

    public static Path getImagesPath() {
        return Paths.get(getSourcePath().toString(), "images");
    }

    private static TreeModel getModel() {
        FileTreeModel model = new FileTreeModel();
        model.init(Paths.get("C:\\projects\\Tabularium"));
        //return new FileTreeModel(getSourcePath());

        return model;
    }

    public static JTree getTree() {
        JTree tree = new JTree(getModel());
        tree.addMouseListener(new FileTreeMouseListener());

        return tree;
    }

//    public static TreeModel getTreeModel() {
//        Path source = TestFramework.getSourcePath();
//        return new FileTreeModel(source);
//    }
}
