package com.github.dlx4.slim.symbol;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: slim
 * @description: 作用域
 * @author: dlx
 * @created: 2020/06/29 23:27
 */
public abstract class Scope extends SlimSymbol {

    // 作用域中的成员，包括：变量，方法，类
    private List<SlimSymbol> symbols = new LinkedList<>();

    public Scope(String name, Scope enclosingScope, ParserRuleContext ctx) {
        super(name, enclosingScope, ctx);
    }

    /**
     * @param symbol
     * @Description: 向scope中添加symbol
     * @return: void
     * @Creator: dlx
     */
    private void addSymbol(SlimSymbol symbol) {
        symbol.setEnclosingScope(this);
        this.symbols.add(symbol);
    }


}