package com.github.dlx4.slim;

import com.github.dlx4.slim.antlr.SlimBaseVisitor;
import com.github.dlx4.slim.antlr.SlimParser;
import com.github.dlx4.slim.type.PrimitiveType;
import com.github.dlx4.slim.type.SlimType;

/**
 * @program: slim
 * @description: AST解析器
 * @author: dlx
 * @created: 2020/06/30 00:42
 */
public class ASTEvaluator extends SlimBaseVisitor<Object> {

//    // 栈帧管理
//    private Stack<StackFrame> stack = new Stack<StackFrame>();


    @Override
    public Object visitBlock(SlimParser.BlockContext ctx) {
        return null;
    }

    @Override
    public Object visitBlockStatement(SlimParser.BlockStatementContext ctx) {
        Object ret = null;
        if (ctx.statement() != null) {
            ret = visitStatement(ctx.statement());
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

            SlimType type = PrimitiveType.Integer;

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

                case SlimParser.AND:
                    ret = (Boolean) leftObject && (Boolean) rightObject;
                    break;
                case SlimParser.OR:
                    ret = (Boolean) leftObject || (Boolean) rightObject;
                    break;

                default:
                    break;
            }

        } else if (ctx.primary() != null) {
            ret = visitPrimary(ctx.primary());
        }
        return ret;
    }

    @Override
    public Object visitExpressionList(SlimParser.ExpressionListContext ctx) {
        return null;
    }

    @Override
    public Object visitForInit(SlimParser.ForInitContext ctx) {
        return null;
    }

    @Override
    public Object visitLiteral(SlimParser.LiteralContext ctx) {
        Object ret = null;

        // 整数
        if (ctx.integerLiteral() != null) {
            ret = visitIntegerLiteral(ctx.integerLiteral());
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
        return null;
    }

    @Override
    public Object visitPrimary(SlimParser.PrimaryContext ctx) {
        Object ret = null;
        // 字面量
        if (ctx.literal() != null) {
            ret = visitLiteral(ctx.literal());
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
        return ret;
    }

    @Override
    public Object visitTypeType(SlimParser.TypeTypeContext ctx) {
        return null;
    }

    @Override
    public Object visitVariableDeclarator(SlimParser.VariableDeclaratorContext ctx) {
        return null;
    }

    @Override
    public Object visitVariableDeclaratorId(SlimParser.VariableDeclaratorIdContext ctx) {
        return null;
    }

    @Override
    public Object visitVariableDeclarators(SlimParser.VariableDeclaratorsContext ctx) {
        return null;
    }

    @Override
    public Object visitVariableInitializer(SlimParser.VariableInitializerContext ctx) {
        return null;
    }

    @Override
    public Object visitBlockStatements(SlimParser.BlockStatementsContext ctx) {
        Object ret = null;
        for (SlimParser.BlockStatementContext child : ctx.blockStatement()) {
            ret = visitBlockStatement(child);
        }

        return ret;
    }

    @Override
    public Object visitProg(SlimParser.ProgContext ctx) {
        Object ret = visitBlockStatements(ctx.blockStatements());
        return ret;
    }

    @Override
    public Object visitFunctionCall(SlimParser.FunctionCallContext ctx) {
        return null;
    }


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

        private static Boolean EQ(Object obj1, Object obj2, SlimType targetType) {
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
            //对于对象实例、函数，直接比较对象引用
            else {
                ret = obj1 == obj2;
            }

            return ret;
        }

        private static Object GE(Object obj1, Object obj2, SlimType targetType) {
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

        private static Object GT(Object obj1, Object obj2, SlimType targetType) {
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

        private static Object LE(Object obj1, Object obj2, SlimType targetType) {
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

        private static Object LT(Object obj1, Object obj2, SlimType targetType) {
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