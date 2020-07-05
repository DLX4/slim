package com.github.dlx4.slim.symbol;

import com.github.dlx4.slim.SlimUtils;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @program: slim
 * @description: 主程序scope
 * @author: dlx
 * @created: 2020/06/30 23:06
 */
public class RootScope extends BlockScope {

    public RootScope(Scope enclosingScope, ParserRuleContext ctx) {
        super(SlimUtils.generateSymbolName(RootScope.class), enclosingScope, ctx);
    }
}