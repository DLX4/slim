package com.github.dlx4.slim.symbol;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @program: slim
 * @description: 主程序scope
 * @author: dlx
 * @created: 2020/06/30 23:06
 */
public class RootScope extends BlockScope {

    public RootScope(String name, Scope enclosingScope, ParserRuleContext ctx) {
        super(name, enclosingScope, ctx);
    }
}