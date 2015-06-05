package controller;

import model.Attribute;
import model.Script;
import view.filetree.FileTreeMouseListener;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFramework {
    private static final Path _source =  Alexandria.TestFramework.getPath(Alexandria.TestFramework.Folders.SOURCE);

    public static Path getSourcePath() {
        return _source;
    }

    public static Path getLiteraturePath() {
        return Paths.get(getSourcePath().toString(), "Literature.html");
    }

    private static TreeModel getModel() {
        FileTreeModel model = new FileTreeModel();
        model.init(_source);

        return model;
    }

    public static JTree getTree() {
        JTree tree = new JTree(getModel());
        tree.addMouseListener(new FileTreeMouseListener());

        return tree;
    }

    public static String getLiteratureScript() {
        return "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<attributes>\n" +
                "\t<output>true</output>\n" +
                "\t<MainTemplate>{$Templates}/main.template</MainTemplate>\n" +
                "\t<CurrentPage>Литература</CurrentPage>\n" +
                "\t<Breadcrumbs>{$Breadcrumbs}/mainpath.thtml</Breadcrumbs>\n" +
                "\t<MainMenu>{$Templates}/mm_literature.template</MainMenu>\n" +
                "\t<Content>\n" +
                "\t\t<from><![CDATA[<td colspan=\"4\">]]></from>\n" +
                "\t\t<to><![CDATA[</table>]]></to>\n" +
                "\t\t<value>{$Blanks}/para.blank</value>\n" +
                "\t\t<path>{$BasePath}\\literatute.thtml</path>\n" +
                "\t</Content>\n" +
                "</attributes>";
    }

    public static Script getScript(){
        Attribute attribute = new Attribute();
        attribute.put("attribute", "value");

        Script script = new Script();
        script.put("param", attribute);

        return script;
    }
}
