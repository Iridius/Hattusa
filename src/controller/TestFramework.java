package controller;

import model.Attribute;
import model.Config;
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

    public static String getLiteratuteText() {
        return "<td colspan=\"4\"><h3>Инки</h3></td>\n" +
                "<tr>\n" +
                "<td> </td>\n" +
                "<td colspan=\"4\">\n" +
                "<ol>\n" +
                "<li><strong>Альперович М. С., Слезкин Л. Ю.</strong> <em>История Латинской Америки (с древнейших времен до начала XX в.).</em> М., &laquo;Высшая школа&raquo;, 1991.</li>\n" +
                "<li><strong>Башилов В. А.</strong> <em>Древние цивилизации Перу и Боливии.</em>М., 1972.</li>\n" +
                "<li><strong>Боден Л.</strong> <em>Инки. Быт. Культура. Религия.</em>М., &laquo;Центрполиграф&raquo;, 2004.</li>\n" +
                "<li><strong>Зубрицкий Ю. А.</strong> <em>Инки-кечуа.</em> М., 1975.</li>\n" +
                "<li><strong>Инки: Владыки золота и наследники славы.</strong> <em>Энциклопедия &laquo;Исчезнувшие цивилизации&raquo;.</em> М., &laquo;TERRA-ТЕРРА&raquo;, 1997.</li>\n" +
                "</ol>\n" +
                "</td>\n" +
                "\n" +
                "<td colspan=\"4\"><h3>Ассирия</h3></td>\n" +
                "<ol>\n" +
                "<li><strong>Васильев Л. С.</strong> <em>История Востока. т. 1.</em>, М., 1998.</li>\n" +
                "<li><strong>Вейс Г.</strong> <em>История культуры народов мира. Ассирия. Вавилон. Персия. Первые сверхдержавы.</em> М., &laquo;Эксмо&raquo;, 2005.</li>\n" +
                "<li><strong>Дьяконов И. М.</strong> <em>К вопросу о судьбе пленных в Ассирии и Урарту.</em> //ВДИ. 1952, № 1.</li>\n" +
                "<li><strong>Дьяконов И. М.</strong> <em>Развитие земельных отношений в Ассирии.</em> Л., 1949</li>\n" +
                "<li><strong>Зайцев А., Лаптева В., Порьяз А.</strong> <em>Мировая культура. Шумерское царство. Вавилон и Ассирия. Древний Египет.</em> М., &laquo;ОЛМА-Пресс&raquo;, 2000.</li>\n" +
                "<li><em>История Востока в шести томах. т. 1. Восток в древности.</em> (под ред. Р. Б. Рыбакова и др.). М., &laquo;Восточная литература&raquo; РАН, 2002</li>\n" +
                "</ol></td>\n" +
                "\n" +
                "<td align=\"right\"><img alt=\"\" src=\"images/Assiria.jpg\" border=\"1\" hspace=\"10\"></td>\n" +
                "</tr>" +
                "</table>";
    }

    private static String getLiteratuteScriptText() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<attributes>\n" +
                "\t<output>true</output>\n" +
                "\t<path>{@BasePath}\\literature.thtml</path>\n" +
                "\t\n" +
                "\t<MainTemplate>{$Templates}\\main.template</MainTemplate>\n" +
                "\t<CurrentPage>Литература</CurrentPage>\n" +
                "\t<Breadcrumbs>{$Breadcrumbs}\\mainpath.thtml</Breadcrumbs>\n" +
                "\t<MainMenu>{$Templates}\\mm_literature.template</MainMenu>\n" +
                "\n" +
                "\t<Content>\n" +
                "\t\t<from><![CDATA[<td colspan=\"4\">]]></from>\n" +
                "\t\t<to><![CDATA[</table>]]></to>\n" +
                "\t\t<value>{$Blanks}\\para.blank</value>\n" +
                "\t\t<path>{$BasePath}\\literatute.thtml</path>\n" +
                "\t</Content>\n" +
                "</attributes>";
    }

    public static Script getLiteratureScript() {
        String text = getLiteratuteScriptText();
        XmlParser parser = new XmlParser(text);

        return parser.getContent();
    }

    public static Script getScript(){
        Attribute attribute = new Attribute();
        attribute.put("attribute", "value");

        Script script = new Script();
        script.put("param", attribute);

        return script;
    }

    public static Decompiler getDecompiler(){
        String text = getLiteratuteText();
        Script script = getLiteratureScript();

        return new Decompiler(text, script);
    }
}
