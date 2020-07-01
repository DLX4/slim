package com.github.dlx4.slim;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: slim
 * @description: 通用工具类
 * @author: dlx
 * @created: 2020/06/29 00:05
 */
public class SlimUtils {

    // 自增ID
    private static Map<String, Integer> AUTO_INC_ID = new HashMap<>();

    private static class AstTreePrinter {
        /**
         * Platform dependent end-of-line marker
         */
        public static final String Eol = System.lineSeparator();
        /**
         * The literal indent char(s) used for pretty-printing
         */
        public static final String Indents = "  ";
        private static int level;

        private AstTreePrinter() {
        }

        public static String toPrettyTree(final Tree t, final List<String> ruleNames) {
            level = 0;
            return process(t, ruleNames).replaceAll("(?m)^\\s+$", "").replaceAll("\\r?\\n\\r?\\n", Eol);
        }

        private static String process(final Tree t, final List<String> ruleNames) {
            if (t.getChildCount() == 0) return Utils.escapeWhitespace(Trees.getNodeText(t, ruleNames), false);
            StringBuilder sb = new StringBuilder();
            sb.append(lead(level));
            level++;
            String s = Utils.escapeWhitespace(Trees.getNodeText(t, ruleNames), false);
            sb.append(s + ' ');
            for (int i = 0; i < t.getChildCount(); i++) {
                sb.append(process(t.getChild(i), ruleNames));
            }
            level--;
            sb.append(lead(level));
            return sb.toString();
        }

        private static String lead(int level) {
            StringBuilder sb = new StringBuilder();
            if (level > 0) {
                sb.append(Eol);
                for (int cnt = 0; cnt < level; cnt++) {
                    sb.append(Indents);
                }
            }
            return sb.toString();
        }
    }

    /**
     * @param parseTree
     * @Description: 打印AST
     * @return: void
     * @Creator: dlx
     */
    public static void printAst(ParseTree parseTree, Parser parser) {
        System.out.println(parseTree.toStringTree(parser));
    }

    /**
     * @param parseTree
     * @Description: 打印AST
     * @return: void
     * @Creator: dlx
     */
    public static void printAstPretty(ParseTree parseTree, Parser parser) {
        List<String> ruleNamesList = Arrays.asList(parser.getRuleNames());
        String prettyTree = AstTreePrinter.toPrettyTree(parseTree, ruleNamesList);
        System.out.println(prettyTree);
    }

    /**
     * 读文本文件
     *
     * @param pathName
     * @return
     * @throws IOException
     */
    public static String readTextFile(String pathName) throws IOException {
        StringBuffer buffer = new StringBuffer();
        try (FileReader reader = new FileReader(pathName);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line).append('\n');
            }
        }
        return buffer.toString();
    }

    /**
     * 写文本文件
     *
     * @param pathName
     * @param text
     * @throws IOException
     */
    public static void writeTextFile(String pathName, String text) throws IOException {
        File file = new File(pathName);
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter out = new BufferedWriter(writer)) {
            StringReader reader = new StringReader(text);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                out.write(line);
                out.newLine();
            }
            out.flush(); // 把缓存区内容压入文件
        }
    }

    /**
     * @param prefix
     * @Description: 获取自增ID
     * @return: int
     * @Creator: dlx
     */
    private static int getAutoIncID(String prefix) {
        if (!AUTO_INC_ID.containsKey(prefix)) {
            AUTO_INC_ID.put(prefix, 0);
        }
        int id = AUTO_INC_ID.get(prefix);
        AUTO_INC_ID.put(prefix, id + 1);
        return id;
    }

    /**
     * @param cls
     * @Description: 生成symbol的name
     * @return: java.lang.String
     * @Creator: dlx
     */
    public static String generateSymbolName(Class cls) {
        String name = cls.getName();
        return name + getAutoIncID(name);
    }

}