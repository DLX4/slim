package com.github.dlx4.slim.symbol;

import com.github.dlx4.slim.SlimUtils;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @program: slim
 * @description: 块作用域
 * @author: dlx
 * @created: 2020/06/29 23:51
 */
public class BlockScope extends Scope {

    public BlockScope() {
        super(SlimUtils.generateSymbolName(BlockScope.class), null, null);
    }

    public BlockScope(String name, Scope enclosingScope, ParserRuleContext ctx) {
        super(name, enclosingScope, ctx);
    }
}