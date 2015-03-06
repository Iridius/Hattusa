package controller;

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

//    public static TreeModel getTreeModel() {
//        Path source = TestFramework.getSourcePath();
//        return new FileTreeModel(source);
//    }
}
