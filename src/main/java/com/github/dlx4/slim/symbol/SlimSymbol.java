package com.github.dlx4.slim.symbol;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @program: slim
 * @description: 最基本的符号
 * @author: dlx
 * @created: 2020/06/29 23:25
 */
@Getter
@Setter
public class SlimSymbol {

    // 符号名称
    private final String name;
    // 关联的AST节点
    private final ParserRuleContext ctx;
    // 所属作用域
    private Scope enclosingScope;

    public SlimSymbol(String name, Scope enclosingScope, ParserRuleContext ctx) {
        this.name = name;
        this.enclosingScope = enclosingScope;
        this.ctx = ctx;
    }

}