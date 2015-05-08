package model;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void testGetLastProjectPath() throws Exception {
        Path actual = Config.getLastProjectPath();
        Path expected = Paths.get("C:\\projects\\Tabularium");
        assertEquals("Expected another last project path.", expected, actual);
    }

//    @Test
//    public void testGetBlanksPath() throws Exception {
//        Path actual = Config.getBlanksPath();
//        Path expected = Paths.get("C:\\projects\\Tabularium");
//
//        assertEquals("Expected another last project path.", expected, actual);
//    }
}