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

        assertEquals("Expected source path will be root element in JTree.", source.toString(), model.getRoot().toString());
    }

    @Test
    public void testGetChild() {
        TreeModel model = new FileTreeModel(TestFramework.getSourcePath());

        String expected = "annotations";
        String actual = model.getChild(model.getRoot(), 0).toString();

        assertEquals("Expected first element of child nodes will be folder 'annotations'.", expected, actual);
    }

    @Test
    public void testGetChild_name_without_parent() {
        TreeModel model = new FileTreeModel(TestFramework.getSourcePath());

        Object articles = model.getChild(model.getRoot(), 1);
        Object images = model.getChild(articles, 0);

        assertTrue("Expected element will contains own name \"images\".", images.toString().contains("images"));
        assertFalse("Expected element name will not contains folder name.", images.toString().contains("articles"));
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
        TreeModel model = new FileTreeModel(source);
        Object images = model.getChild(model.getRoot(), 1);

        assertFalse("Expected node " + images.toString() + " would not be leaf.", model.isLeaf(images));
    }

    @Test
    public void testValueForPathChanged() throws Exception {

    }

    @Test
    public void testGetIndexOfChild() throws Exception {
        TreeModel model = new FileTreeModel(TestFramework.getSourcePath());

        int expected = 1;
        Object images = model.getChild(model.getRoot(), expected);
        int actual = model.getIndexOfChild(model.getRoot(), images);

        assertEquals("Expected another file direction in folder.", expected, actual);
    }

    @Test
    public void testAddTreeModelListener() throws Exception {

    }

    @Test
    public void testRemoveTreeModelListener() throws Exception {

    }
}