package com.github.dlx4.slim;

import com.github.dlx4.slim.runtime.Return;
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
public class FunctionTest extends BaseTest {

    @ValueSource(strings = {"function.00.test"})
    @ParameterizedTest
    void function00(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        Assertions.assertEquals(result, 12);
    }


    @ValueSource(strings = {"function.01.test"})
    @ParameterizedTest
    void function01(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        Assertions.assertEquals(((Return) result).getValue(), 8);
    }

    @ValueSource(strings = {"function.02.test"})
    @ParameterizedTest
    void function02(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        Assertions.assertEquals(((Return) result).getValue(), 4);
    }

    @ValueSource(strings = {"function.03.test"})
    @ParameterizedTest
    void function03(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        Assertions.assertEquals(result, 4);
    }

    @ValueSource(strings = {"function.04.test"})
    @ParameterizedTest
    void function04(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        Assertions.assertEquals(result, 6);
    }
}