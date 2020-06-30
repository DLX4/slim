package com.github.dlx4.slim.scanner;

import com.github.dlx4.slim.AnnotatedTree;
import com.github.dlx4.slim.antlr.SlimParser;
import com.github.dlx4.slim.symbol.BlockScope;
import com.github.dlx4.slim.symbol.Scope;
import com.github.dlx4.slim.symbol.SlimSymbol;
import com.github.dlx4.slim.symbol.Variable;
import com.github.dlx4.slim.type.PrimitiveType;
import com.github.dlx4.slim.type.SlimType;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * @program: slim
 * @description: 引用消解扫描
 * @author: dlx
 * @created: 2020/06/30 22:48
 */
public class RefResolveScanner extends AbstractAstScanner {

    private final ParseTreeWalker walker = new ParseTreeWalker();
    private final LocalVariableScanner localVariableScanner;

    public RefResolveScanner(AnnotatedTree annotatedTree) {
        super(annotatedTree);
        localVariableScanner = new LocalVariableScanner(annotatedTree);
    }

    // 把本地变量加到符号表。本地变量必须是边添加，边解析，不能先添加后解析，否则会引起引用消解的错误。
    @Override
    public void enterVariableDeclarators(SlimParser.VariableDeclaratorsContext ctx) {
        Scope scope = annotatedTree.getEnclosingScope(ctx);
        if (scope instanceof BlockScope) {
            walker.walk(localVariableScanner, ctx);
        }
    }

    @Override
    public void exitPrimary(SlimParser.PrimaryContext ctx) {
        Scope scope = annotatedTree.getEnclosingScope(ctx);
        SlimType type = null;

        // 标识符
        if (ctx.IDENTIFIER() != null) {
            String idName = ctx.IDENTIFIER().getText();

            Variable variable = annotatedTree.lookupVariable(scope, idName);
            if (variable == null) {
                annotatedTree.log("unknown variable or function: " + idName, ctx);
            } else {
                annotatedTree.relateSymbolToNode(variable, ctx);
                type = variable.getType();
            }
        }
        // 字面量
        else if (ctx.literal() != null) {
            type = annotatedTree.getType(ctx.literal());
        }
        // 括号里的表达式
        else if (ctx.expression() != null) {
            type = annotatedTree.getType(ctx.expression());
        }

        // 类型推断、冒泡
        annotatedTree.relateTypeToNode(type, ctx);
    }

    //消解处理点符号表达式的层层引用
    @Override
    public void exitExpression(SlimParser.ExpressionContext ctx) {
        SlimType type = null;

        // 变量引用冒泡： 如果下级是一个变量，往上冒泡传递，以便在点符号表达式中使用
        if (ctx.primary() != null) {
            SlimSymbol symbol = annotatedTree.getSymbol(ctx.primary());
            annotatedTree.relateSymbolToNode(symbol, ctx);
        }

        // 类型推断和综合
        if (ctx.primary() != null) {
            type = annotatedTree.getType(ctx.primary());
        } else if (ctx.bop != null && ctx.expression().size() >= 2) {
            SlimType type1 = annotatedTree.getType(ctx.expression(0));
            SlimType type2 = annotatedTree.getType(ctx.expression(1));

            switch (ctx.bop.getType()) {
                case SlimParser.ADD:
                    if (type1 == PrimitiveType.String || type2 == PrimitiveType.String) {
                        type = PrimitiveType.String;
                    } else if (type1 instanceof PrimitiveType && type2 instanceof PrimitiveType) {
                        // 类型向上对齐，比如一个int和一个float，取float
                        type = PrimitiveType.getUpperType(type1, type2);
                    } else {
                        annotatedTree.log("operand should be PrimitiveType for additive and multiplicative operation", ctx);
                    }
                    break;
                case SlimParser.SUB:
                case SlimParser.MUL:
                case SlimParser.DIV:
                    if (type1 instanceof PrimitiveType && type2 instanceof PrimitiveType) {
                        //类型向上对齐，比如一个int和一个float，取float
                        type = PrimitiveType.getUpperType(type1, type2);
                    } else {
                        annotatedTree.log("operand should be PrimitiveType for additive and multiplicative operation", ctx);
                    }

                    break;
                case SlimParser.EQUAL:
                case SlimParser.NOTEQUAL:
                case SlimParser.LE:
                case SlimParser.LT:
                case SlimParser.GE:
                case SlimParser.GT:
                case SlimParser.AND:
                case SlimParser.OR:
                case SlimParser.BANG:
                    type = PrimitiveType.Boolean;
                    break;
                case SlimParser.ASSIGN:
                case SlimParser.ADD_ASSIGN:
                case SlimParser.SUB_ASSIGN:
                case SlimParser.MUL_ASSIGN:
                case SlimParser.DIV_ASSIGN:
                case SlimParser.AND_ASSIGN:
                case SlimParser.OR_ASSIGN:
                case SlimParser.XOR_ASSIGN:
                case SlimParser.MOD_ASSIGN:
                case SlimParser.LSHIFT_ASSIGN:
                case SlimParser.RSHIFT_ASSIGN:
                case SlimParser.URSHIFT_ASSIGN:
                    type = type1;
                    break;
            }
        }

        // 类型冒泡
        annotatedTree.relateTypeToNode(type, ctx);
    }

    // 对变量初始化部分也做一下类型推断
    @Override
    public void exitVariableInitializer(SlimParser.VariableInitializerContext ctx) {
        if (ctx.expression() != null) {
            annotatedTree.relateTypeToNode(annotatedTree.getType(ctx.expression()), ctx);
        }
    }

    // 根据字面量来推断类型
    @Override
    public void exitLiteral(SlimParser.LiteralContext ctx) {
        if (ctx.BOOL_LITERAL() != null) {
            annotatedTree.relateTypeToNode(PrimitiveType.Boolean, ctx);
        } else if (ctx.CHAR_LITERAL() != null) {
            annotatedTree.relateTypeToNode(PrimitiveType.Char, ctx);
        } else if (ctx.NULL_LITERAL() != null) {
            annotatedTree.relateTypeToNode(PrimitiveType.Null, ctx);
        } else if (ctx.STRING_LITERAL() != null) {
            annotatedTree.relateTypeToNode(PrimitiveType.String, ctx);
        } else if (ctx.integerLiteral() != null) {
            annotatedTree.relateTypeToNode(PrimitiveType.Integer, ctx);
        } else if (ctx.floatLiteral() != null) {
            annotatedTree.relateTypeToNode(PrimitiveType.Float, ctx);
        }

    }

}