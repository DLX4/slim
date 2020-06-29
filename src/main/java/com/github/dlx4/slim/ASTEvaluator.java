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

    @Override
    public Object visitBlock(SlimParser.BlockContext ctx) {
        return null;
    }

    @Override
    public Object visitBlockStatement(SlimParser.BlockStatementContext ctx) {
        return null;
    }

    @Override
    public Object visitExpression(SlimParser.ExpressionContext ctx) {
        return null;
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
        return null;
    }


    @Override
    public Object visitIntegerLiteral(SlimParser.IntegerLiteralContext ctx) {
        Object rtn = null;
        if (ctx.DECIMAL_LITERAL() != null) {
            rtn = Integer.valueOf(ctx.DECIMAL_LITERAL().getText());
        }
        return rtn;
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
        return null;
    }

    @Override
    public Object visitPrimitiveType(SlimParser.PrimitiveTypeContext ctx) {
        return null;
    }

    @Override
    public Object visitStatement(SlimParser.StatementContext ctx) {
        return null;
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
        return null;
    }

    @Override
    public Object visitProg(SlimParser.ProgContext ctx) {
        return null;
    }

    @Override
    public Object visitFunctionCall(SlimParser.FunctionCallContext ctx) {
        return null;
    }


    private static class EvaluatorHelper {
        private Object add(Object obj1, Object obj2, SlimType targetType) {
            Object rtn = null;
            if (targetType == PrimitiveType.String) {
                rtn = String.valueOf(obj1) + String.valueOf(obj2);
            } else if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() + ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() + ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() + ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() + ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() + ((Number) obj2).shortValue();
            } else {
                System.out.println("unsupported add operation");
            }

            return rtn;
        }

        private Object minus(Object obj1, Object obj2, SlimType targetType) {
            Object rtn = null;
            if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() - ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() - ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() - ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() - ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() - ((Number) obj2).shortValue();
            }

            return rtn;
        }

        private Object mul(Object obj1, Object obj2, SlimType targetType) {
            Object rtn = null;
            if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() * ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() * ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() * ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() * ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() * ((Number) obj2).shortValue();
            }

            return rtn;
        }

        private Object div(Object obj1, Object obj2, SlimType targetType) {
            Object rtn = null;
            if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() / ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() / ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() / ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() / ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() / ((Number) obj2).shortValue();
            }

            return rtn;
        }

        private Boolean EQ(Object obj1, Object obj2, SlimType targetType) {
            Boolean rtn = null;
            if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() == ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() == ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() == ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() == ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() == ((Number) obj2).shortValue();
            }
            //对于对象实例、函数，直接比较对象引用
            else {
                rtn = obj1 == obj2;
            }

            return rtn;
        }

        private Object GE(Object obj1, Object obj2, SlimType targetType) {
            Object rtn = null;
            if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() >= ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() >= ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() >= ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() >= ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() >= ((Number) obj2).shortValue();
            }

            return rtn;
        }

        private Object GT(Object obj1, Object obj2, SlimType targetType) {
            Object rtn = null;
            if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() > ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() > ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() > ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() > ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() > ((Number) obj2).shortValue();
            }

            return rtn;
        }

        private Object LE(Object obj1, Object obj2, SlimType targetType) {
            Object rtn = null;
            if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() <= ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() <= ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() <= ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() <= ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() <= ((Number) obj2).shortValue();
            }

            return rtn;
        }

        private Object LT(Object obj1, Object obj2, SlimType targetType) {
            Object rtn = null;
            if (targetType == PrimitiveType.Integer) {
                rtn = ((Number) obj1).intValue() < ((Number) obj2).intValue();
            } else if (targetType == PrimitiveType.Float) {
                rtn = ((Number) obj1).floatValue() < ((Number) obj2).floatValue();
            } else if (targetType == PrimitiveType.Long) {
                rtn = ((Number) obj1).longValue() < ((Number) obj2).longValue();
            } else if (targetType == PrimitiveType.Double) {
                rtn = ((Number) obj1).doubleValue() < ((Number) obj2).doubleValue();
            } else if (targetType == PrimitiveType.Short) {
                rtn = ((Number) obj1).shortValue() < ((Number) obj2).shortValue();
            }

            return rtn;
        }
    }
}