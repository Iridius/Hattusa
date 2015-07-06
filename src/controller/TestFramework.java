package controller;

import model.Attribute;
import model.Script;
import view.filetree.FileTreeMouseListener;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFramework {
    private static final Path _source =  Paths.get("c:\\projects\\Tabularium");
    private static Script literatureBlankScript;

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

    public static String getLiteratureBlankText() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<attributes>\n" +
                "\t<output>true</output>\n" +
                "\t<sys:path>{$BasePath}\\literature.thtml</sys:path>\n" +
                "\t<MainTemplate>{$Templates}\\main.template</MainTemplate>\n" +
                "\t<CurrentPage>Литература</CurrentPage>\n" +
                "\t<MainMenu>{$Templates}\\mm_literature.template</MainMenu>\n" +
                "\t<Content>\n" +
                "\t\t<sys:from><![CDATA[<td colspan=\"4\">]]></sys:from>\n" +
                "\t\t<sys:to><![CDATA[</table>]]></sys:to>\n" +
                "\t\t<value>{$Blanks}\\Literature\\para.blank</value>\n" +
                "\t\t<filter>thtml</filter>\n" +
                "\t</Content>\n" +
                "</attributes>";
    }

    public static Script getLiteratureBlankScript() {
        return XmlParser.getScript(getLiteratureBlankText());
    }

    public static String getIncaScriptText(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<attributes>\n" +
                "\t<output>false</output>\n" +
                "\t<sys:path>{$BasePath}\\Literature\\Para\\Инки.thtml</sys:path>\n" +
                "\t<MainTemplate>{$Templates}\\Literature\\Para.template</MainTemplate>\n" +
                "\t<Name>Инки</Name>\n" +
                "\t<Books>\n" +
                "\t\t<filter>thtml</filter>\n" +
                "\t\t<sort>{$Author}</sort>\n" +
                "\t\t<template>{$Templates}\\Literature\\Book.template</template>\n" +
                "\t\t<value>{$BasePath}\\Literature\\Books\\Инки\\</value>\n" +
                "\t</Books>\n" +
                "</attributes>";
    }

    public static String getAssyriaScriptText(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<attributes>\n" +
                "\t<output>false</output>\n" +
                "\t<sys:path>{$BasePath}\\Literature\\Para\\Ассирия.thtml</sys:path>\n" +
                "\t<MainTemplate>{$Templates}\\Literature\\Para.template</MainTemplate>\n" +
                "\t<Name>Ассирия</Name>\n" +
                "\t<Books>\n" +
                "\t\t<filter>thtml</filter>\n" +
                "\t\t<sort>{$Author}</sort>\n" +
                "\t\t<template>{$Templates}\\Literature\\Book.template</template>\n" +
                "\t\t<value>{$BasePath}\\Literature\\Books\\Ассирия\\</value>\n" +
                "\t</Books>\n" +
                "</attributes>";
    }

    public static String getIncaBook_1_ScriptText(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<attributes>\n" +
                "\t<output>false</output>\n" +
                "\t<MainTemplate>{$Templates}\\Literature\\Book.template</MainTemplate>\n" +
                "\t<sys:path>{$BasePath}\\Literature\\Books\\Инки\\Инки_0.thtml</sys:path>\n" +
                "\t<Author>Альперович М. С., Слезкин Л. Ю.</Author>\n" +
                "\t<Name>История Латинской Америки (с древнейших времен до начала XX в.).</Name>\n" +
                "\t<Publishing>Высшая школа</Publishing>\n" +
                "</attributes>";
    }

    public static String getIncaBook_2_ScriptText(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<attributes>\n" +
                "\t<output>false</output>\n" +
                "\t<MainTemplate>{$Templates}\\Literature\\Book.template</MainTemplate>\n" +
                "\t<sys:path>{$BasePath}\\Literature\\Books\\Инки\\Инки_1.thtml</sys:path>\n" +
                "\t<Author>Башилов В. А.</Author>\n" +
                "\t<Name>Древние цивилизации Перу и Боливии.</Name>\n" +
                "\t<Publishing></Publishing>\n" +
                "</attributes>";
    }

    static String getLiteratureScriptText(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<attributes>\n" +
                "\t<output>true</output>\n" +
                "\t<sys:path>{$BasePath}\\literature.thtml</sys:path>\n" +
                "\t<MainTemplate>{$Templates}\\main.template</MainTemplate>\n" +
                "\t<CurrentPage>Литература</CurrentPage>\n" +
                "\t<MainMenu>{$Templates}\\mm_literature.template</MainMenu>\n" +
                "\t<Content>\n" +
                "\t\t<value>{$BasePath}\\Literature\\Para\\</value>\n" +
                "\t\t<filter>thtml</filter>\n" +
                "\t</Content>\n" +
                "</attributes>";
    }

    static String getBookBlankText(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<attributes>\n" +
                "\t<output>false</output>\n" +
                "\t<MainTemplate>{$Templates}\\Literature\\Book.template</MainTemplate>\n" +
                "\t<sys:path>{$BasePath}\\Literature\\{$Parent}\\</sys:path>\n" +
                "\t<sys:file>{$Parent}_{$i}.thtml</sys:file>\n" +
                "\t<sys:from><![CDATA[<li>]]></sys:from>\n" +
                "\t<sys:to><![CDATA[</li>]]></sys:to>\n" +
                "\t<Author>\n" +
                "\t\t<sys:from><![CDATA[<strong>]]></sys:from>\n" +
                "\t\t<sys:to><![CDATA[</strong>]]></sys:to>\n" +
                "\t</Author>\n" +
                "\t<Name>\n" +
                "\t\t<sys:from><![CDATA[<em>]]></sys:from>\n" +
                "\t\t<sys:to><![CDATA[</em>]]></sys:to>\n" +
                "\t</Name>\n" +
                "\t<Publishing>\n" +
                "\t\t<sys:from><![CDATA[&laquo;]]></sys:from>\n" +
                "\t\t<sys:to><![CDATA[&raquo;]]></sys:to>\n" +
                "\t</Publishing>\n" +
                "</attributes>";
    }

    public static String getBookScriptText(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<attributes>\n" +
                "\t<output>false</output>\n" +
                "\t<MainTemplate>{$Templates}\\Literature\\Book.template</MainTemplate>\n" +
                "\t<sys:path>{$BasePath}\\Literature\\{$Parent}\\</sys:path>\n" +
                "\t<Author>Альперович М. С., Слезкин Л. Ю.</Author>\n" +
                "\t<Name>История Латинской Америки (с древнейших времен до начала XX в.).</Name>\n" +
                "\t<Publishing>Высшая школа</Publishing>\n" +
                "</attributes>";
    }

    public static Script getBookScript(){
        return XmlParser.getScript(getBookBlankText());
    }

    public static Script getLiteratureScript() {
        return XmlParser.getScript(getLiteratureScriptText());
    }

//    public static Script getParaScript() {
//        return XmlParser.getScript(getParaBlankText());
//    }

    public static Script getScript(){
        Attribute attribute = new Attribute("name");
        attribute.put("attribute", "value");

        Script script = new Script();
        script.put("param", attribute);

        return script;
    }
}
