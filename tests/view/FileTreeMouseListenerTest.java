package view;

import controller.TestFramework;
import org.junit.Assert;
import org.junit.Test;
import view.filetree.FileTreeMouseListener;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.nio.file.Path;

public class FileTreeMouseListenerTest {

    @Test
    public void testMousePressed() {
        JTree tree = TestFramework.getTree();
        tree.setVisible(true);
        FileTreeMouseListener listener = (FileTreeMouseListener) tree.getMouseListeners()[1];

        MouseEvent event = new MouseEvent(tree, MouseEvent.BUTTON1, 1, 0, 80, 10, 1, true);
        listener.mouseClicked(event);
        Path actual = listener.getSelectedPath();
        Path expected = TestFramework.getSourcePath();

        Assert.assertEquals("Expected will be selected real path.", expected, actual);
    }
}