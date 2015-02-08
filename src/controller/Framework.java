package controller;

import model.Frame;
import model.JsonParser;
import view.FrameModel;
import view.MainForm;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class Framework {
    public static void createGUI() {
        MainForm.createGUI();

        for(Path path: getForms("out\\artifacts\\views\\")) {
            Frame frame = JsonParser.parseFrame(path);
            FrameModel.createGUI(frame);
        }
    }

    private static Collection<Path> getForms(String path) {
        Path directory = Paths.get(path).toAbsolutePath();
        return Alexandria.Library.getFiles(directory);
    }
}
