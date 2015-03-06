package controller;

import org.junit.Test;

import javax.swing.tree.TreeModel;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;


public class FileTreeModelTest {

    @Test
    public void testGetRoot() {
        Path source = TestFramework.getSourcePath();
        TreeModel model = new FileTreeModel(source);

        assertEquals("Expected source path will be root element in tree.", source, model.getRoot());
    }

    @Test
    public void testGetChild() {
        Path source = TestFramework.getSourcePath();
        TreeModel model = new FileTreeModel(source);

        Path expected = Alexandria.TestFramework.getPath(Alexandria.TestFramework.Folders.ANNOTATION).getFileName();
        Path actual = (Path) model.getChild(source, 0);

        assertEquals("Expected first element of child nodes will be folder 'Annotation'.", expected, actual);
    }

    @Test
    public void testGetChildCount() throws Exception {

    }

    @Test
    public void testIsLeaf() throws Exception {

    }

    @Test
    public void testValueForPathChanged() throws Exception {

    }

    @Test
    public void testGetIndexOfChild() throws Exception {

    }

    @Test
    public void testAddTreeModelListener() throws Exception {

    }

    @Test
    public void testRemoveTreeModelListener() throws Exception {

    }
}