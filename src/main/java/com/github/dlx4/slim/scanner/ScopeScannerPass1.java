package com.github.dlx4.slim.scanner;

import com.github.dlx4.slim.AnnotatedTree;
import com.github.dlx4.slim.antlr.SlimParser;
import com.github.dlx4.slim.symbol.BlockScope;
import com.github.dlx4.slim.symbol.Function;
import com.github.dlx4.slim.symbol.RootScope;
import com.github.dlx4.slim.symbol.Scope;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

/**
 * @program: slim
 * @description: 扫描作用域
 * @author: dlx
 * @created: 2020/06/30 22:48
 */
public class ScopeScannerPass1 extends AbstractAstScanner {

    private final Stack<Scope> scopeStack = new Stack<>();

    public ScopeScannerPass1(AnnotatedTree annotatedTree) {
        super(annotatedTree);
    }

    /**
     * @param scope
     * @Description: 进栈
     * @return: com.github.dlx4.slim.symbol.Scope
     * @Creator: dlx
     */
    private Scope pushScope(Scope scope, ParserRuleContext ctx) {
        this.scopeStack.push(scope);
        this.annotatedTree.relateScopeToNode(scope, ctx);
        return scope;
    }

    /**
     * @Description: 出栈
     * @return: void
     * @Creator: dlx
     */
    private void popScope() {
        this.scopeStack.pop();
    }

    /**
     * @Description: 当前遍历的scope
     * @return: com.github.dlx4.slim.symbol.Scope
     * @Creator: dlx
     */
    private Scope currentScope() {
        if (scopeStack.size() > 0) {
            return scopeStack.peek();
        } else {
            return null;
        }
    }

    @Override
    public void enterProg(SlimParser.ProgContext ctx) {
        RootScope scope = new RootScope(currentScope(), ctx);
        pushScope(scope, ctx);
    }

    @Override
    public void exitProg(SlimParser.ProgContext ctx) {
        popScope();
    }

    @Override
    public void enterBlock(SlimParser.BlockContext ctx) {
        if (!(ctx.parent instanceof SlimParser.FunctionBodyContext)) {
            BlockScope scope = new BlockScope(currentScope(), ctx);
            currentScope().addSymbol(scope);
            pushScope(scope, ctx);
        }
    }

    @Override
    public void exitBlock(SlimParser.BlockContext ctx) {
        if (!(ctx.parent instanceof SlimParser.FunctionBodyContext)) {
            popScope();
        }
    }


    @Override
    public void enterStatement(SlimParser.StatementContext ctx) {
        // 为for建立额外的Scope
        if (ctx.FOR() != null) {
            BlockScope scope = new BlockScope(currentScope(), ctx);
            currentScope().addSymbol(scope);
            pushScope(scope, ctx);
        }
    }

    @Override
    public void exitStatement(SlimParser.StatementContext ctx) {
        // 释放for语句的外层scope
        if (ctx.FOR() != null) {
            popScope();
        }
    }

    @Override
    public void enterFunctionDeclaration(SlimParser.FunctionDeclarationContext ctx) {
        String idName = ctx.IDENTIFIER().getText();

        // 现在只考虑function的scope, function的类型信息后续再处理 @See RefResolveScannerPass3
        Function function = new Function(idName, currentScope(), ctx);

        annotatedTree.getFunctionTypes().add(function);

        currentScope().addSymbol(function);
        pushScope(function, ctx);
    }

    @Override
    public void exitFunctionDeclaration(SlimParser.FunctionDeclarationContext ctx) {
        popScope();
    }


//    @Override
//    public void enterClassDeclaration(SlimParser.ClassDeclarationContext ctx) {
//
//    }
//
//    @Override
//    public void exitClassDeclaration(SlimParser.ClassDeclarationContext ctx) {
//
//    }

}