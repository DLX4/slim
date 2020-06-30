package com.github.dlx4.slim.scanner;

import com.github.dlx4.slim.AnnotatedTree;
import com.github.dlx4.slim.antlr.SlimBaseListener;

/**
 * @program: slim
 * @description: 扫描AST
 * @author: dlx
 * @created: 2020/06/30 22:36
 */
public abstract class AbstractAstScanner extends SlimBaseListener {

    protected final AnnotatedTree annotatedTree;

    public AbstractAstScanner(AnnotatedTree annotatedTree) {
        this.annotatedTree = annotatedTree;
    }
}