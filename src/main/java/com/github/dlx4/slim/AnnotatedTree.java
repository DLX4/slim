package com.github.dlx4.slim;

import com.github.dlx4.slim.symbol.Scope;
import lombok.Data;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: slim
 * @description: 注释树（语义分析结果）
 * @author: dlx
 * @created: 2020/06/29 00:39
 */
@Data
public class AnnotatedTree {

    // AST
    private final ParseTree ast;

    // AST节点对应的Scope
    private final Map<ParserRuleContext, Scope> nodeRelateScope = new HashMap<>();

    public AnnotatedTree(ParseTree ast) {
        this.ast = ast;
    }

    /**
     * @param scope  @param ctx
     * @Description: 将AST节点与scope关联
     * @return: com.github.dlx4.slim.symbol.Scope
     * @Creator: dlx
     */
    public void relateScopeToNode(Scope scope, ParserRuleContext ctx) {
        this.nodeRelateScope.put(ctx, scope);
    }

}