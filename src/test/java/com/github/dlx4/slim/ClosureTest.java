package com.github.dlx4.slim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

/**
 * @program: slim
 * @description: 测试编译器
 * @author: dlx
 * @created: 2020/06/28 23:44
 */
public class ClosureTest extends BaseTest {

    @ValueSource(strings = {"closure.00.test"})
    @ParameterizedTest
    void closure00(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        Assertions.assertEquals(result, 1);
    }

    @ValueSource(strings = {"closure.01.test"})
    @ParameterizedTest
    void closure01(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        Assertions.assertEquals(result, 9);
    }

}