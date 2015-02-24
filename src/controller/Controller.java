package controller;

import model.FrameModel;
import model.JsonParser;
import view.FrameView;
import view.MainView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class Controller {
    public static void init() {
        MainView.createGUI();

        for(Path path: getForms(getConfigPath())) {
            FrameModel frame = JsonParser.parseFrame(path);
            FrameView.createGUI(frame);
        }
    }

    private static Collection<Path> getForms(String path) {
        Path directory = Paths.get(path).toAbsolutePath();
        return Alexandria.Library.getFiles(directory);
    }

    private static String getConfigPath() {
        return "out\\artifacts\\views\\";
    }
}
