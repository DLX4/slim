package com.github.dlx4.slim;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

/**
 * @program: slim
 * @description: 测试编译器
 * @author: dlx
 * @created: 2020/06/28 23:44
 */
public class ExpressionTest extends BaseTest {

    // 支持整数表达式
    @ValueSource(strings = {"expression.00.test"})
    @ParameterizedTest
    void expression00(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        System.out.println(result);
        // 17
    }

    // 支持浮点数表达式
    @ValueSource(strings = {"expression.01.test"})
    @ParameterizedTest
    void expression01(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        System.out.println(result);
    }

}