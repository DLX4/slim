package com.github.dlx4.slim;

import com.github.dlx4.slim.antlr.SlimParser;
import com.github.dlx4.slim.runtime.FunctionRtStore;
import com.github.dlx4.slim.symbol.Function;
import com.github.dlx4.slim.symbol.Scope;
import com.github.dlx4.slim.symbol.SlimSymbol;
import com.github.dlx4.slim.symbol.Variable;
import com.github.dlx4.slim.type.SlimType;
import lombok.Data;
import org.antlr.v4.runtime.ParserRuleContext;
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

    // AST节点推断出来的类型
    protected Map<ParserRuleContext, SlimType> nodeRelateType = new HashMap<>();

    // 编译信息，包括普通信息、警告和错误
    protected List<CompilationLog> logs = new LinkedList<>();

    public AnnotatedTree(ParseTree ast) {
        this.ast = ast;
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
     * @param ctx
     * @Description: 根据函数调用的上下文，返回一个FunctionObject。
     * 对于函数类型的变量，这个functionObject是存在变量里的；
     * 对于普通的函数调用，此时创建一个。
     * @return: com.github.dlx4.slim.symbol.Function
     * @Creator: dlx
     */
    public FunctionRtStore getFunction(SlimParser.FunctionCallContext ctx) {
        if (ctx.IDENTIFIER() == null) return null;  //暂时不支持this和super

        Function function = null;
        FunctionRtStore store = null;

        SlimSymbol symbol = getSymbol(ctx);
        // 函数类型的变量
        if (symbol instanceof Variable) {
            // TODO
//            Variable variable = (Variable) symbol;
//            LValue lValue = getLValue(variable);
//            Object value = lValue.getValue();
//            if (value instanceof FunctionObject) {
//                functionObject = (FunctionObject) value;
//                function = functionObject.function;
//            }
        }
        // 普通函数
        else if (symbol instanceof Function) {
            function = (Function) symbol;
        }
        // 报错
        else {
            // 这是调用时的名称，不一定是真正的函数名，还可能是函数类型的变量名
            String functionName = ctx.IDENTIFIER().getText();
            log("unable to find function or function variable " + functionName, ctx);
            return null;
        }

        if (store == null) {
            store = new FunctionRtStore(function);
        }

        return store;
    }


    /**
     * @param scope
     * @param idName
     * @Description: 逐级scope查找variable
     * @return: com.github.dlx4.slim.symbol.Variable
     * @Creator: dlx
     */
    public Variable lookupVariable(Scope scope, String idName) {
        Variable ret = scope.getVariable(idName);

        if (ret == null && scope.getEnclosingScope() != null) {
            ret = lookupVariable(scope.getEnclosingScope(), idName);
        }
        return ret;
    }

    /**
     * 通过方法的名称和方法签名查找Function。逐级Scope查找。
     *
     * @param scope
     * @param idName
     * @param paramTypes
     * @return
     */
    public Function lookupFunction(Scope scope, String idName, List<SlimType> paramTypes) {
        Function ret = scope.getFunction(idName, paramTypes);

        if (ret == null && scope.getEnclosingScope() != null) {
            ret = lookupFunction(scope.getEnclosingScope(), idName, paramTypes);
        }
        return ret;
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