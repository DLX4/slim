package com.github.dlx4.slim;

import com.github.dlx4.slim.antlr.SlimLexer;
import com.github.dlx4.slim.antlr.SlimParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @program: slim
 * @description: 编译器
 * @author: dlx
 * @created: 2020/06/29 00:41
 */
public class SlimCompiler {


    /**
     * @param script
     * @Description: 编译
     * @return: com.github.dlx4.slim.AnnotatedTree
     * @Creator: dlx
     */
    public AnnotatedTree compile(String script) {
        AnnotatedTree at = new AnnotatedTree();

        // 词法分析
        SlimLexer lexer = new SlimLexer(CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 语法分析
        SlimParser parser = new SlimParser(tokens);
        ParseTree ast = parser.prog();
        SlimUtils.printAST(ast, parser);

        return at;
    }

    /**
     * @param at
     * @Description: 执行
     * @return: java.lang.Object
     * @Creator: dlx
     */
    public Object execute(AnnotatedTree at) {
        return null;
    }


}