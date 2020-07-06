package com.github.dlx4.slim.runtime;

import com.github.dlx4.slim.AnnotatedTree;
import com.github.dlx4.slim.antlr.SlimBaseVisitor;
import com.github.dlx4.slim.antlr.SlimParser;
import com.github.dlx4.slim.symbol.BlockScope;
import com.github.dlx4.slim.symbol.Function;
import com.github.dlx4.slim.symbol.SlimSymbol;
import com.github.dlx4.slim.symbol.Variable;
import com.github.dlx4.slim.type.PrimitiveType;
import com.github.dlx4.slim.type.SlimType;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: slim
 * @description: AST解析器
 * @author: dlx
 * @created: 2020/06/30 00:42
 */
public class SlimEvaluator extends SlimBaseVisitor<Object> {

    private final AnnotatedTree annotatedTree;
    private final RtStack rtStack;

    public SlimEvaluator(AnnotatedTree annotatedTree) {
        this.annotatedTree = annotatedTree;
        this.rtStack = new RtStack();
    }

    @Override
    public Object visitBlock(SlimParser.BlockContext ctx) {
        BlockScope scope = (BlockScope) annotatedTree.getScope(ctx);
        if (scope != null) {
            StackFrame frame = new StackFrame(scope);
            rtStack.push(frame);
        }

        Object ret = visitBlockStatements(ctx.blockStatements());

        if (scope != null) {
            rtStack.pop();
        }

        return ret;
    }

    @Override
    public Object visitBlockStatement(SlimParser.BlockStatementContext ctx) {
        Object ret = null;
        if (ctx.statement() != null) {
            ret = visitStatement(ctx.statement());
        } else if (ctx.variableDeclarators() != null) {
            ret = visitVariableDeclarators(ctx.variableDeclarators());
        }
        return ret;
    }

    @Override
    public Object visitExpression(SlimParser.ExpressionContext ctx) {
        Object ret = null;

        if (ctx.bop != null && ctx.expression().size() >= 2) {
            Object left = visitExpression(ctx.expression(0));
            Object right = visitExpression(ctx.expression(1));

            Object leftObject = left;
            Object rightObject = right;

            if (left instanceof LeftValue) {
                leftObject = ((LeftValue) left).getValue();
            }

            if (right instanceof LeftValue) {
                rightObject = ((LeftValue) right).getValue();
            }

            // 本节点数据类型
            SlimType type = annotatedTree.getType(ctx);
            // 子节点的数据类型
            SlimType type1 = annotatedTree.getType(ctx.expression(0));
            SlimType type2 = annotatedTree.getType(ctx.expression(1));

            switch (ctx.bop.getType()) {
                case SlimParser.ADD:
                    ret = EvaluatorHelper.add(leftObject, rightObject, type);
                    break;
                case SlimParser.SUB:
                    ret = EvaluatorHelper.minus(leftObject, rightObject, type);
                    break;
                case SlimParser.MUL:
                    ret = EvaluatorHelper.mul(leftObject, rightObject, type);
                    break;
                case SlimParser.DIV:
                    ret = EvaluatorHelper.div(leftObject, rightObject, type);
                    break;
                case SlimParser.EQUAL:
                    ret = EvaluatorHelper.eq(leftObject, rightObject, PrimitiveType.getUpperType(type1, type2));
                    break;
                case SlimParser.NOTEQUAL:
                    ret = !EvaluatorHelper.eq(leftObject, rightObject, PrimitiveType.getUpperType(type1, type2));
                    break;
                case SlimParser.LE:
                    ret = EvaluatorHelper.le(leftObject, rightObject, PrimitiveType.getUpperType(type1, type2));
                    break;
                case SlimParser.LT:
                    ret = EvaluatorHelper.lt(leftObject, rightObject, PrimitiveType.getUpperType(type1, type2));
                    break;
                case SlimParser.GE:
                    ret = EvaluatorHelper.ge(leftObject, rightObject, PrimitiveType.getUpperType(type1, type2));
                    break;
                case SlimParser.GT:
                    ret = EvaluatorHelper.gt(leftObject, rightObject, PrimitiveType.getUpperType(type1, type2));
                    break;
                case SlimParser.AND:
                    ret = (Boolean) leftObject && (Boolean) rightObject;
                    break;
                case SlimParser.OR:
                    ret = (Boolean) leftObject || (Boolean) rightObject;
                    break;
                case SlimParser.ASSIGN:
                    if (left instanceof LeftValue) {
                        ((LeftValue) left).setValue(rightObject);
                        ret = right;
                    } else {
                        System.out.println("Unsupported feature during assignment");
                    }
                    break;
                default:
                    break;
            }

        } else if (ctx.primary() != null) {
            ret = visitPrimary(ctx.primary());
        } else if (ctx.functionCall() != null) {
            ret = visitFunctionCall(ctx.functionCall());
        }

        // 后缀运算，例如：i++ 或 i--
        else if (ctx.postfix != null) {
            // 操作数1
            Object value = visitExpression(ctx.expression(0));
            SlimType type = annotatedTree.getType(ctx.expression(0));

            LeftValue leftValue = null;
            if (value instanceof LeftValue) {
                leftValue = (LeftValue) value;
                value = leftValue.getValue();
            }
            switch (ctx.postfix.getType()) {
                case SlimParser.INC:
                    if (type == PrimitiveType.Integer) {
                        leftValue.setValue((Integer) value + 1);
                    } else {
                        leftValue.setValue((Long) value + 1);
                    }
                    ret = value;
                    break;
                case SlimParser.DEC:
                    if (type == PrimitiveType.Integer) {
                        leftValue.setValue((Integer) value - 1);
                    } else {
                        leftValue.setValue((long) value - 1);
                    }
                    ret = value;
                    break;
                default:
                    break;
            }
        }

        // 前缀操作，例如：++i 或 --i 或!i
        else if (ctx.prefix != null) {
            Object value = visitExpression(ctx.expression(0));
            SlimType type = annotatedTree.getType(ctx.expression(0));

            LeftValue leftValue = null;
            if (value instanceof LeftValue) {
                leftValue = (LeftValue) value;
                value = leftValue.getValue();
            }
            switch (ctx.prefix.getType()) {
                case SlimParser.INC:
                    if (type == PrimitiveType.Integer) {
                        ret = (Integer) value + 1;
                    } else {
                        ret = (Long) value + 1;
                    }
                    leftValue.setValue(ret);
                    break;
                case SlimParser.DEC:
                    if (type == PrimitiveType.Integer) {
                        ret = (Integer) value - 1;
                    } else {
                        ret = (Long) value - 1;
                    }
                    leftValue.setValue(ret);
                    break;
                // !符号，逻辑非运算
                case SlimParser.BANG:
                    ret = !((Boolean) value);
                    break;
                default:
                    break;
            }
        }

        return ret;
    }

    @Override
    public Object visitExpressionList(SlimParser.ExpressionListContext ctx) {
        Object ret = null;
        for (SlimParser.ExpressionContext child : ctx.expression()) {
            ret = visitExpression(child);
        }
        return ret;
    }

    @Override
    public Object visitForInit(SlimParser.ForInitContext ctx) {
        Object ret = null;
        if (ctx.variableDeclarators() != null) {
            ret = visitVariableDeclarators(ctx.variableDeclarators());
        } else if (ctx.expressionList() != null) {
            ret = visitExpressionList(ctx.expressionList());
        }
        return ret;
    }

    @Override
    public Object visitLiteral(SlimParser.LiteralContext ctx) {
        Object ret = null;

        // 整数
        if (ctx.integerLiteral() != null) {
            ret = visitIntegerLiteral(ctx.integerLiteral());
        }

        // 浮点数
        else if (ctx.floatLiteral() != null) {
            ret = visitFloatLiteral(ctx.floatLiteral());
        }

        // 字符串
        else if (ctx.STRING_LITERAL() != null) {
            String withQuotationMark = ctx.STRING_LITERAL().getText();
            ret = withQuotationMark.substring(1, withQuotationMark.length() - 1);
        }

        return ret;
    }


    @Override
    public Object visitIntegerLiteral(SlimParser.IntegerLiteralContext ctx) {
        Object ret = null;
        if (ctx.DECIMAL_LITERAL() != null) {
            ret = Integer.valueOf(ctx.DECIMAL_LITERAL().getText());
        }
        return ret;
    }

    @Override
    public Object visitFloatLiteral(SlimParser.FloatLiteralContext ctx) {
        return Float.valueOf(ctx.getText());
    }

    @Override
    public Object visitParExpression(SlimParser.ParExpressionContext ctx) {
        return visitExpression(ctx.expression());
    }

    @Override
    public Object visitPrimary(SlimParser.PrimaryContext ctx) {
        Object ret = null;
        // 字面量
        if (ctx.literal() != null) {
            ret = visitLiteral(ctx.literal());
        }
        // 变量 (普通变量及函数)
        else if (ctx.IDENTIFIER() != null) {
            SlimSymbol symbol = annotatedTree.getSymbol(ctx);
            if (symbol instanceof Variable) {
                ret = rtStack.getLeftValue((Variable) symbol);
            } else if (symbol instanceof Function) {
                FunctionRtStore obj = new FunctionRtStore((Function) symbol);
                ret = obj;
            }
        }

        return ret;
    }

    @Override
    public Object visitPrimitiveType(SlimParser.PrimitiveTypeContext ctx) {
        return null;
    }

    @Override
    public Object visitStatement(SlimParser.StatementContext ctx) {
        Object ret = null;
        if (ctx.statementExpression != null) {
            ret = visitExpression(ctx.statementExpression);
        }

        // if语句
        else if (ctx.IF() != null) {
            Boolean condition = (Boolean) visitParExpression(ctx.parExpression());
            if (Boolean.TRUE == condition) {
                ret = visitStatement(ctx.statement(0));
            } else if (ctx.ELSE() != null) {
                ret = visitStatement(ctx.statement(1));
            }
        }

        // block (blockLabel是别名)
        else if (ctx.blockLabel != null) {
            ret = visitBlock(ctx.blockLabel);
        }

        // for循环
        else if (ctx.FOR() != null) {
            // 压栈
            BlockScope scope = (BlockScope) annotatedTree.getScope(ctx);
            StackFrame frame = new StackFrame(scope);
            rtStack.push(frame);

            SlimParser.ForControlContext forControl = ctx.forControl();
            if (forControl.enhancedForControl() != null) {
                // 增强版for循环
            } else {
                // 初始化部分执行一次
                if (forControl.forInit() != null) {
                    ret = visitForInit(forControl.forInit());
                }

                while (true) {
                    Boolean condition = true;
                    if (forControl.expression() != null) {
                        Object value = visitExpression(forControl.expression());
                        if (value instanceof LeftValue) {
                            condition = (Boolean) ((LeftValue) value).getValue();
                        } else {
                            condition = (Boolean) value;
                        }
                    }

                    if (condition) {
                        // 执行for的语句体
                        ret = visitStatement(ctx.statement(0));

                        // break退出
                        if (ret instanceof Break) {
                            ret = null;
                            break;
                        }

                        // return退出
                        else if (ret instanceof Return) {
                            break;
                        }

                        // 执行forUpdate
                        if (forControl.forUpdate != null) {
                            visitExpressionList(forControl.forUpdate);
                        }
                    } else {
                        break;
                    }
                }
            }

            // 出栈
            rtStack.pop();
        }

        // break语句
        else if (ctx.BREAK() != null) {
            ret = Break.instance();
        }

        // return语句
        else if (ctx.RETURN() != null) {
            if (ctx.expression() != null) {
                ret = visitExpression(ctx.expression());

                if (ret instanceof LeftValue) {
                    ret = ((LeftValue) ret).getValue();
                }
            }

            // 包装return语句的返回值
            ret = new Return.ReturnBuilder().value(ret).build();
        }
        return ret;
    }

    @Override
    public Object visitTypeType(SlimParser.TypeTypeContext ctx) {
        return null;
    }

    @Override
    public Object visitVariableDeclarator(SlimParser.VariableDeclaratorContext ctx) {
        Object ret = null;
        LeftValue leftValue = (LeftValue) visitVariableDeclaratorId(ctx.variableDeclaratorId());
        if (ctx.variableInitializer() != null) {
            ret = visitVariableInitializer(ctx.variableInitializer());
            if (ret instanceof LeftValue) {
                ret = ((LeftValue) ret).getValue();
            }
            leftValue.setValue(ret);
        }
        return ret;
    }

    @Override
    public Object visitVariableDeclaratorId(SlimParser.VariableDeclaratorIdContext ctx) {
        Object ret = null;
        SlimSymbol symbol = annotatedTree.getSymbol(ctx);
        ret = rtStack.getLeftValue((Variable) symbol);
        return ret;
    }

    @Override
    public Object visitVariableDeclarators(SlimParser.VariableDeclaratorsContext ctx) {
        Object ret = null;
        for (SlimParser.VariableDeclaratorContext child : ctx.variableDeclarator()) {
            ret = visitVariableDeclarator(child);
        }
        return ret;
    }

    @Override
    public Object visitVariableInitializer(SlimParser.VariableInitializerContext ctx) {
        Object ret = null;
        if (ctx.expression() != null) {
            ret = visitExpression(ctx.expression());
        }
        return ret;
    }

    @Override
    public Object visitBlockStatements(SlimParser.BlockStatementsContext ctx) {
        Object ret = null;
        for (SlimParser.BlockStatementContext child : ctx.blockStatement()) {
            ret = visitBlockStatement(child);

            // break跳过下面语句
            if (ret instanceof Break) {
                break;
            }

            // return跳过下面语句
            else if (ret instanceof Return) {
                break;
            }
        }

        return ret;
    }

    @Override
    public Object visitProg(SlimParser.ProgContext ctx) {
        rtStack.push(new StackFrame((BlockScope) annotatedTree.getScope(ctx)));

        Object ret = visitBlockStatements(ctx.blockStatements());

        rtStack.pop();
        return ret;
    }

    /**
     * 执行一个函数的方法体。需要先设置参数值，然后再执行代码。
     *
     * @param store
     * @param paramValues
     * @return
     */
    private Object functionCall(FunctionRtStore store, List<Object> paramValues) {
        Object ret;

        //添加函数的栈桢
        StackFrame functionFrame = new StackFrame(store);
        rtStack.push(functionFrame);

        // 给参数赋值，这些值进入functionFrame
        SlimParser.FunctionDeclarationContext functionCode = (SlimParser.FunctionDeclarationContext) store.getFunction().getCtx();
        if (functionCode.formalParameters().formalParameterList() != null) {
            for (int i = 0; i < functionCode.formalParameters().formalParameterList().formalParameter().size(); i++) {
                SlimParser.FormalParameterContext param = functionCode.formalParameters().formalParameterList().formalParameter(i);
                LeftValue lValue = (LeftValue) visitVariableDeclaratorId(param.variableDeclaratorId());
                lValue.setValue(paramValues.get(i));
            }
        }

        // 调用函数（方法）体
        ret = visitFunctionDeclaration(functionCode);

        // 弹出StackFrame
        rtStack.pop(); //函数的栈桢

        // 如果由一个return语句返回，真实返回值会被封装在一个ReturnObject里。
        if (ret instanceof Return) {
            ret = ((Return) ret).getValue();
        }

        return ret;
    }

    /**
     * 计算运行时某个函数调用时的参数值
     *
     * @param ctx
     * @return
     */
    public List<Object> calcParamValues(SlimParser.FunctionCallContext ctx) {
        List<Object> paramValues = new LinkedList<Object>();
        if (ctx.expressionList() != null) {
            for (SlimParser.ExpressionContext exp : ctx.expressionList().expression()) {
                Object value = visitExpression(exp);
                if (value instanceof LeftValue) {
                    value = ((LeftValue) value).getValue();
                }
                paramValues.add(value);
            }
        }
        return paramValues;
    }

    @Override
    public Object visitFunctionCall(SlimParser.FunctionCallContext ctx) {
        Object ret;

        String functionName = ctx.IDENTIFIER().getText();
        if (functionName.equals("println")) {
            if (ctx.expressionList() != null) {
                Object value = visitExpressionList(ctx.expressionList());
                System.out.println(value);
            } else {
                System.out.println();
            }
            return null;
        }

        FunctionRtStore store = getFunction(ctx);

        // 计算参数值
        List<Object> paramValues = calcParamValues(ctx);

        System.out.println("\n>>FunctionCall : " + ctx.getText());

        ret = functionCall(store, paramValues);

        return ret;
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

        SlimSymbol symbol = annotatedTree.getSymbol(ctx);
        // 函数类型的变量
        if (symbol instanceof Variable) {
            Variable variable = (Variable) symbol;
            LeftValue leftValue = rtStack.getLeftValue(variable);
            Object value = leftValue.getValue();
            if (value instanceof FunctionRtStore) {
                store = (FunctionRtStore) value;
                function = store.getFunction();
            }
        }
        // 普通函数
        else if (symbol instanceof Function) {
            function = (Function) symbol;
        }
        // 报错
        else {
            // 这是调用时的名称，不一定是真正的函数名，还可能是函数类型的变量名
            String functionName = ctx.IDENTIFIER().getText();
            annotatedTree.log("unable to find function or function variable " + functionName, ctx);
            return null;
        }

        if (store == null) {
            store = new FunctionRtStore(function);
        }

        return store;
    }

    /**
     * 辅助类
     */
    private static class EvaluatorHelper {

        private static Object add(Object obj1, Object obj2, SlimType targetType) {
            Object ret = null;
            if (targetType == PrimitiveType.String) {
                ret = String.valueOf(obj1) + String.valueOf(obj2);
            } else if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() + ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() + ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() + ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() + ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() + ((Number) obj2).shortValue();
            } else {
                System.out.println("unsupported add operation");
            }

            return ret;
        }

        private static Object minus(Object obj1, Object obj2, SlimType targetType) {
            Object ret = null;
            if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() - ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() - ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() - ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() - ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() - ((Number) obj2).shortValue();
            }

            return ret;
        }

        private static Object mul(Object obj1, Object obj2, SlimType targetType) {
            Object ret = null;
            if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() * ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() * ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() * ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() * ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() * ((Number) obj2).shortValue();
            }

            return ret;
        }

        private static Object div(Object obj1, Object obj2, SlimType targetType) {
            Object ret = null;
            if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() / ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() / ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() / ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() / ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() / ((Number) obj2).shortValue();
            }

            return ret;
        }

        private static Boolean eq(Object obj1, Object obj2, SlimType targetType) {
            Boolean ret = null;
            if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() == ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() == ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() == ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() == ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() == ((Number) obj2).shortValue();
            }
            // 对于对象实例、函数，直接比较对象引用
            else {
                ret = obj1 == obj2;
            }

            return ret;
        }

        private static Object ge(Object obj1, Object obj2, SlimType targetType) {
            Object ret = null;
            if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() >= ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() >= ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() >= ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() >= ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() >= ((Number) obj2).shortValue();
            }

            return ret;
        }

        private static Object gt(Object obj1, Object obj2, SlimType targetType) {
            Object ret = null;
            if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() > ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() > ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() > ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() > ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() > ((Number) obj2).shortValue();
            }

            return ret;
        }

        private static Object le(Object obj1, Object obj2, SlimType targetType) {
            Object ret = null;
            if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() <= ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() <= ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() <= ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() <= ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() <= ((Number) obj2).shortValue();
            }

            return ret;
        }

        private static Object lt(Object obj1, Object obj2, SlimType targetType) {
            Object ret = null;
            if (targetType == PrimitiveType.Integer) {
                ret = ((Number) obj1).intValue() < ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                ret = ((Number) obj1).floatValue() < ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                ret = ((Number) obj1).longValue() < ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                ret = ((Number) obj1).doubleValue() < ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                ret = ((Number) obj1).shortValue() < ((Number) obj2).shortValue();
            }

            return ret;
        }
    }
}