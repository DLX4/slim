package com.github.dlx4.slim.symbol;

import com.github.dlx4.slim.type.SlimType;
import lombok.Getter;
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
@Getter
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
     * @param symbol
     * @Description: 是否包含某个Symbol
     * @return: boolean
     * @Creator: dlx
     */
    public boolean containsSymbol(SlimSymbol symbol) {
        return symbols.contains(symbol);
    }

    /**
     * @param name
     * @Description: 从scope中查找variable
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


    /**
     * @param name
     * @param paramTypes
     * @Description: 从scope中查找function
     * @return: com.github.dlx4.slim.symbol.Function
     * @Creator: dlx
     */
    public Function getFunction(String name, List<SlimType> paramTypes) {
        for (SlimSymbol s : symbols) {
            if (s instanceof Function && Objects.equals(s.getName(), name)) {
                Function function = (Function) s;
                if (function.isMatch(paramTypes)) {
                    return function;
                }
            }
        }
        return null;
    }

    /**
     * @param idName
     * @Description: 逐级scope查找variable
     * @return: com.github.dlx4.slim.symbol.Variable
     * @Creator: dlx
     */
    public Variable lookupVariable(String idName) {
        Variable ret = this.getVariable(idName);

        if (ret == null && this.getEnclosingScope() != null) {
            ret = this.getEnclosingScope().lookupVariable(idName);
        }
        return ret;
    }

    /**
     * 通过方法的名称和方法签名查找Function。逐级Scope查找。
     *
     * @param idName
     * @param paramTypes
     * @return
     */
    public Function lookupFunction(String idName, List<SlimType> paramTypes) {
        Function ret = this.getFunction(idName, paramTypes);

        if (ret == null && this.getEnclosingScope() != null) {
            ret = this.getEnclosingScope().lookupFunction(idName, paramTypes);
        }
        return ret;
    }

    /**
     * 逐级查找函数。仅通过名字查找。如果有重名的，返回第一个就算了。
     * TODO 如果有重名的需要报错。
     *
     * @param name
     * @return
     */
    public Function lookupFunction(String name) {
        Function ret;
        ret = this.getFunctionOnlyByName(name);

        if (ret == null && this.getEnclosingScope() != null) {
            ret = this.getEnclosingScope().lookupFunction(name);
        }

        return ret;
    }

    private Function getFunctionOnlyByName(String name) {
        for (SlimSymbol s : symbols) {
            if (s instanceof Function && Objects.equals(s.getName(), name)) {
                return (Function) s;
            }
        }
        return null;
    }

}