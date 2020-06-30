package com.github.dlx4.slim.symbol;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    public void addSymbol(SlimSymbol symbol) {
        symbol.setEnclosingScope(this);
        this.symbols.add(symbol);
    }

    /**
     * @param name
     * @Description: 从scope中获取variable
     * @return: com.github.dlx4.slim.symbol.Variable
     * @Creator: dlx
     */
    public Variable getVariable(String name) {
        for (SlimSymbol s : symbols) {
            if (s instanceof Variable && Objects.equals(s.getName(), name)) {
                return (Variable) s;
            }
        }
        return null;
    }

}