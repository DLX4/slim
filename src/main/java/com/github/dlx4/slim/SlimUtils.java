package com.github.dlx4.slim;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.util.HashMap;
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

    /**
     * @param parseTree
     * @Description: 打印AST
     * @return: void
     * @Creator: dlx
     */
    public static void printAST(ParseTree parseTree, Parser parser) {
        System.out.println(parseTree.toStringTree(parser));
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