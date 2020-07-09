package com.github.dlx4.slim;

import com.github.dlx4.slim.symbol.Scope;
import com.github.dlx4.slim.symbol.SlimSymbol;
import com.github.dlx4.slim.type.SlimType;
import lombok.Data;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

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

    // AST节点对应的Symbol
    private final Map<ParserRuleContext, SlimSymbol> nodeRelateSymbol = new HashMap<>();

    // AST节点对应的Scope
    private final Map<ParserRuleContext, Scope> nodeRelateScope = new HashMap<>();
    // 解析到的所有类型
    private final List<SlimType> functionTypes = new ArrayList<>();
    // 编译信息，包括普通信息、警告和错误
    protected List<CompilationLog> logs = new LinkedList<>();
    // AST节点推断出来的类型
    private Map<ParserRuleContext, SlimType> nodeRelateType = new HashMap<>();

    public AnnotatedTree(ParseTree ast) {
        this.ast = ast;
    }

    /**
     * 判断node1是不是node2的祖先
     *
     * @param node1
     * @param node2
     */
    public static boolean isAncestor(RuleContext node1, RuleContext node2) {
        if (node2.parent == null) {
            return false;
        } else if (node2.parent == node1) {
            return true;
        } else {
            return isAncestor(node1, node2.parent);
        }
    }

    /**
     * @param scope
     * @param ctx
     * @Description: 将AST节点与scope关联
     * @return: com.github.dlx4.slim.symbol.Scope
     * @Creator: dlx
     */
    public void relateScopeToNode(Scope scope, ParserRuleContext ctx) {
        this.nodeRelateScope.put(ctx, scope);
    }

    /**
     * @param symbol
     * @param ctx
     * @Description: 将AST节点与symbol关联
     * @return: void
     * @Creator: dlx
     */
    public void relateSymbolToNode(SlimSymbol symbol, ParserRuleContext ctx) {
        this.nodeRelateSymbol.put(ctx, symbol);
    }

    /**
     * @param type
     * @param ctx
     * @Description: 将AST节点与type关联
     * @return: void
     * @Creator: dlx
     */
    public void relateTypeToNode(SlimType type, ParserRuleContext ctx) {
        this.nodeRelateType.put(ctx, type);
    }

    /**
     * @param node
     * @Description: 查找某节点所在的Scope
     * @return: com.github.dlx4.slim.symbol.Scope
     * @Creator: dlx
     */
    public Scope getEnclosingScope(ParserRuleContext node) {
        Scope ret = null;
        ParserRuleContext parent = node.getParent();
        if (parent != null) {
            ret = nodeRelateScope.get(parent);
            if (ret == null) {
                ret = getEnclosingScope(parent);
            }
        }
        return ret;
    }

    /**
     * @param node
     * @Description: 查找某节点的type
     * @return: com.github.dlx4.slim.type.SlimType
     * @Creator: dlx
     */
    public SlimType getType(ParserRuleContext node) {
        return nodeRelateType.get(node);
    }

    /**
     * @param node
     * @Description: 查找某节点的symbol
     * @return: com.github.dlx4.slim.symbol.SlimSymbol
     * @Creator: dlx
     */
    public SlimSymbol getSymbol(ParserRuleContext node) {
        return nodeRelateSymbol.get(node);
    }

    /**
     * @param node
     * @Description: 查找某节点的scope
     * @return: com.github.dlx4.slim.symbol.Scope
     * @Creator: dlx
     */
    public Scope getScope(ParserRuleContext node) {
        return nodeRelateScope.get(node);
    }

    /**
     * @param message
     * @param type
     * @param ctx
     * @Description: 记录编译信息
     * @return: void
     * @Creator: dlx
     */
    public void log(String message, int type, ParserRuleContext ctx) {
        CompilationLog log = CompilationLog.builder()
                .ctx(ctx)
                .message(message)
                .line(ctx.getStart().getLine())
                .positionInLine(ctx.getStart().getStartIndex())
                .type(type)
                .build();

        logs.add(log);

        System.out.println(log);
    }

    /**
     * @param message
     * @param ctx
     * @Description: 记录编译错误
     * @return: void
     * @Creator: dlx
     */
    public void log(String message, ParserRuleContext ctx) {
        this.log(message, CompilationLog.ERROR, ctx);
    }

    /**
     * @param
     * @Description: 是否有编译错误
     * @return: boolean
     * @Creator: dlx
     */
    public boolean hasCompilationError() {
        for (CompilationLog log : logs) {
            if (Objects.equals(log.getType(), CompilationLog.ERROR)) {
                return true;
            }
        }
        return false;
    }
}