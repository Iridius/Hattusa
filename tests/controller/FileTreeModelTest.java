package controller;

import org.junit.Test;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;


public class FileTreeModelTest {

    @Test
    public void testGetRoot() throws Exception {
        Path source = TestFramework.getSourcePath();
        FileTreeModel model = new FileTreeModel(source);

        assertEquals("Expected source path will be root element in tree.", source, model.getRoot());
    }

    @Test
    public void testGetChild() throws Exception {

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