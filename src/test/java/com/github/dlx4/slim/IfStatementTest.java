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
public class IfStatementTest extends BaseTest {

    @ValueSource(strings = {"ifStatement.00.test"})
    @ParameterizedTest
    void if00(String fileName) throws IOException {

        String script = this.getTestInput(fileName);

        SlimCompiler compiler = new SlimCompiler();
        AnnotatedTree at = compiler.compile(script);
        Object result = compiler.execute(at);
        Assertions.assertEquals(((Return)result).getValue(), 11);
    }

}