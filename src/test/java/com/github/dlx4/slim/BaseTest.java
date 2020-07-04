package com.github.dlx4.slim;

import java.io.IOException;

/**
 * @program: slim
 * @description: 测试编译器
 * @author: dlx
 * @created: 2020/06/28 23:44
 */
public class BaseTest {

//    @ValueSource(strings = {"expression.test"})
//    @ParameterizedTest
//    void read(String fileName) throws IOException {
//
//        String script = this.getTestInput(fileName);
//
//        System.out.println(script);
//    }

    /**
     * @param fileName
     * @Description: test resource目录下文件的路径
     * @return: java.lang.String
     * @Creator: dlx
     */
    protected String getTestResourceFilePath(String fileName) {
        return this.getClass().getClassLoader().getResource(fileName).getPath();
    }

    /**
     * @param fileName
     * @Description: 获取测试用例脚本内容
     * @return: java.lang.String
     * @Creator: dlx
     */
    protected String getTestInput(String fileName) throws IOException {
        return SlimUtils.readTextFile(this.getTestResourceFilePath(fileName));
    }
}