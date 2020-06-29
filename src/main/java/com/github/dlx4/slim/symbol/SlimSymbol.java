package com.github.dlx4.slim.symbol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @program: slim
 * @description: 最基本的符号
 * @author: dlx
 * @created: 2020/06/29 23:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlimSymbol {

    // 符号名称
    private String name;

    // 所属作用域
    private Scope enclosingScope;

    // 关联的AST节点
    private ParserRuleContext ctx;

}