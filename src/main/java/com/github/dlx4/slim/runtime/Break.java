package com.github.dlx4.slim.runtime;

/**
 * @program: slim
 * @description: 表示break语句
 * @author: dlx
 * @created: 2020/07/04 00:11
 */
public class Break {

    private static Break instance = new Break();

    private Break() {

    }

    public static Break instance() {
        return instance;
    }
}