package com.github.dlx4.slim.symbol;

import com.github.dlx4.slim.type.SlimType;
import lombok.Data;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @program: slim
 * @description: 变量
 * @author: dlx
 * @created: 2020/06/30 22:13
 */
@Data
public class Variable extends SlimSymbol {

    // 变量类型
    protected SlimType type = null;

    public Variable(String name, Scope enclosingScope, ParserRuleContext ctx) {
        super(name, enclosingScope, ctx);
    }

}