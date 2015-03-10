package controller;

import org.junit.Test;

import javax.swing.tree.TreeModel;
import java.nio.file.Path;

import static org.junit.Assert.*;


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
    public void testGetChild_name_without_parent() {
        Path source = TestFramework.getImagesPath();
        TreeModel model = new FileTreeModel(source);

        String actual = model.getChild(source, 0).toString();

        assertTrue("Expected element will contains own name \"arrow\".", actual.contains("arrow"));
        assertFalse("Expected element name will not contains folder name.", actual.contains("images"));
    }

    @Test
    public void testGetChildCount() throws Exception {
        Path source = TestFramework.getSourcePath();
        Path literature = TestFramework.getLiteraturePath();
        TreeModel model = new FileTreeModel(source);

        assertTrue("Expected node " + literature.toString() + " will be leaf.", model.isLeaf(literature));
    }

    @Test
    public void testIsLeaf_true() {
        Path source = TestFramework.getSourcePath();
        Path literature = TestFramework.getLiteraturePath();
        TreeModel model = new FileTreeModel(source);

        assertTrue("Expected node " + literature.toString() + " will be leaf.", model.isLeaf(literature));
    }

    @Test
    public void testIsLeaf_false() {
        Path source = TestFramework.getSourcePath();
        Path images = TestFramework.getImagesPath();
        TreeModel model = new FileTreeModel(source);

        assertFalse("Expected node " + images.toString() + " would not be leaf.", model.isLeaf(images));
    }

    @Test
    public void testValueForPathChanged() throws Exception {

    }

    @Test
    public void testGetIndexOfChild() throws Exception {
        Path source = TestFramework.getSourcePath();
        TreeModel model = new FileTreeModel(source);

        Path file = Alexandria.TestFramework.getSomeHtmlFile();
        Path folder = file.getParent();

        int expected = 6;
        int actual = model.getIndexOfChild(folder, file);

        assertEquals("Expected another file direction in folder.", expected, actual);
    }

    @Test
    public void testAddTreeModelListener() throws Exception {

    }

    @Test
    public void testRemoveTreeModelListener() throws Exception {

    }
}