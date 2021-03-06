package com.github.dlx4.slim;

import com.github.dlx4.slim.antlr.SlimLexer;
import com.github.dlx4.slim.antlr.SlimParser;
import com.github.dlx4.slim.runtime.SlimEvaluator;
import com.github.dlx4.slim.scanner.ClosureAnalyzeScannerPass4;
import com.github.dlx4.slim.scanner.RefResolveScannerPass3;
import com.github.dlx4.slim.scanner.ScopeScannerPass1;
import com.github.dlx4.slim.scanner.VariableScannerPass2;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

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

        // 词法分析
        SlimLexer lexer = new SlimLexer(CharStreams.fromString(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 语法分析
        SlimParser parser = new SlimParser(tokens);
        ParseTree ast = parser.prog();
        SlimUtils.printAst(ast, parser);
        SlimUtils.printAstPretty(ast, parser);

        // 语义分析
        AnnotatedTree annotatedTree = new AnnotatedTree(ast);
        ParseTreeWalker walker = new ParseTreeWalker();

        // 扫描scope
        ScopeScannerPass1 scopeScanner = new ScopeScannerPass1(annotatedTree);
        walker.walk(scopeScanner, ast);

        // 扫描变量，标注类型
        VariableScannerPass2 variableScanner = new VariableScannerPass2(annotatedTree);
        walker.walk(variableScanner, ast);

        // 引用消解扫描
        RefResolveScannerPass3 refResolveScanner = new RefResolveScannerPass3(annotatedTree);
        walker.walk(refResolveScanner, ast);

        // 闭包分析扫描
        ClosureAnalyzeScannerPass4 closureAnalyzeScanner = new ClosureAnalyzeScannerPass4(annotatedTree);
        closureAnalyzeScanner.analyzeClosures();

        return annotatedTree;
    }

    /**
     * @param at
     * @Description: 执行
     * @return: java.lang.Object
     * @Creator: dlx
     */
    public Object execute(AnnotatedTree at) {
        SlimEvaluator visitor = new SlimEvaluator(at);
        Object result = visitor.visit(at.getAst());
        return result;
    }

}