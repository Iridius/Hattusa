package controller;

import Alexandria.Library;
import model.Config;
import view.controls.IRunnable;

import javax.swing.*;
import java.nio.file.Path;
import java.util.Collection;
import java.util.logging.Logger;

public class Blanks implements IRunnable {
    private final static Logger log = Logger.getLogger(Blanks.class.getName());
    private JList _list;

    public Blanks(JList list) {
        _list = list;
    }

    @Override
    public void run() {
           try {
               Path path = Config.getBlanksPath();
               if(path == null) {
                   throw new Exception("Invalid path for template blanks.");
               }

               Collection<Path> blanks = Library.getFiles(path);
               DefaultListModel model = new DefaultListModel();
               final String blanksPath = Config.getBlanksPath().toString();
               for(Path blank: blanks) {
                   String localPath = blank.toString().replace(blanksPath, "");
                   model.addElement(localPath);
               }

               _list.setModel(model);
           } catch (Exception e) {
                log.severe(e.getMessage());
           }
    }
}
