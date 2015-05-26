package controller;

import Alexandria.Library;
import model.Config;
import view.controls.IRunnable;

import javax.swing.*;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

public class Blanks implements IRunnable {
    private final static Logger log = Logger.getLogger(Blanks.class.getName());
    private JList _component;
    private HashMap<String, Path> _elements;

    public Blanks(JList component) {
        _component = component;
        _elements = new HashMap<>();
    }

    public String getContent(String fileName) {
        Path path = _elements.get(fileName);
        return Library.getContent(path);
    }

    @Override
    public void run() {
        _elements = getElements();
        setModel(_component, _elements);
    }

    public Path getValue(){
        if(_component.getSelectedValue() == null) {
            return null;
        }

        return _elements.get(_component.getSelectedValue().toString());
    }

    private void setModel(JList list, HashMap<String, Path> blanks) {
        DefaultListModel model = new DefaultListModel();
        for(String path: blanks.keySet()) {
            model.addElement(path);
        }

        list.setModel(model);
    }

    private HashMap<String, Path> getElements() {
        HashMap<String, Path> result = new HashMap<>();

        try {
            final Path blanksPath = Config.getBlanksPath();
            if(blanksPath == null) {
                throw new Exception("Invalid path for template blanks.");
            }

            Collection<Path> paths = Library.getFiles(blanksPath);
            for(Path path: paths) {
                if(Library.isFolder(path)){
                    continue;
                }

                String localPath = path.toString().replace(blanksPath.toString(), "");
                if(localPath.length() == 0) {
                    continue;
                }
                if(localPath.substring(0,1).equals("\\")) {
                    localPath = localPath.substring(1);
                }

                result.put(localPath, path);
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        }

        return result;
    }
}
