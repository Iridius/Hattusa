package controller;

import java.nio.file.Path;

public class TestFramework {
    public static Path getSourcePath() {
        return Alexandria.TestFramework.getPath(Alexandria.TestFramework.Folders.SOURCE);
    }

//    public static TreeModel getTreeModel() {
//        Path source = TestFramework.getSourcePath();
//        return new FileTreeModel(source);
//    }
}
