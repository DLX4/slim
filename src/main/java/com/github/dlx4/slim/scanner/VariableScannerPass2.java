package com.github.dlx4.slim.scanner;

import com.github.dlx4.slim.AnnotatedTree;
import com.github.dlx4.slim.antlr.SlimParser;
import com.github.dlx4.slim.symbol.Function;
import com.github.dlx4.slim.symbol.Scope;
import com.github.dlx4.slim.symbol.Variable;
import com.github.dlx4.slim.type.DefaultFunctionType;
import com.github.dlx4.slim.type.PrimitiveType;
import com.github.dlx4.slim.type.SlimType;
import com.github.dlx4.slim.type.VoidType;

/**
 * @program: slim
 * @description: 1、变量加入到作用域（同时考虑将函数的形参假如到函数作用域）
 * 2、通过relateTypeToNode标注节点类型（普通变量类型、函数变量类型）
 * 3、通过relateSymbolToNode标注节点对应的变量
 * @author: dlx
 * @created: 2020/06/30 22:48
 */
public class VariableScannerPass2 extends AbstractAstScanner {

    public VariableScannerPass2(AnnotatedTree annotatedTree) {
        super(annotatedTree);
    }

    // 变量声明完毕
    @Override
    public void exitVariableDeclarators(SlimParser.VariableDeclaratorsContext ctx) {
        // 设置的变量类型
        SlimType type = annotatedTree.getType(ctx.typeType());

        // 给声明的一堆变量设置类型
        for (SlimParser.VariableDeclaratorContext child : ctx.variableDeclarator()) {
            Variable variable = (Variable) annotatedTree.getSymbol(child.variableDeclaratorId());
            variable.setType(type);
        }
    }

    // 变量声明
    @Override
    public void enterVariableDeclaratorId(SlimParser.VariableDeclaratorIdContext ctx) {
        String idName = ctx.IDENTIFIER().getText();
        Scope scope = annotatedTree.getEnclosingScope(ctx);

        // 第一步只把类的成员变量入符号表。在变量消解时，再把本地变量加入符号表，一边Enter，一边消解。
        Variable variable = new Variable(idName, scope, ctx);

        // 变量查重
        if (scope.getVariable(idName) != null) {
            annotatedTree.log("Variable or parameter already Declared: " + idName, ctx);
        }

        scope.addSymbol(variable);
        annotatedTree.relateSymbolToNode(variable, ctx);
    }

    @Override
    public void exitFunctionType(SlimParser.FunctionTypeContext ctx) {
        DefaultFunctionType functionType = new DefaultFunctionType();

        annotatedTree.getFunctionTypes().add(functionType);

        annotatedTree.relateTypeToNode(functionType, ctx);

        // 返回值
        functionType.setReturnType(annotatedTree.getType(ctx.typeTypeOrVoid()));

        // 参数
        if (ctx.typeList() != null) {
            SlimParser.TypeListContext tcl = ctx.typeList();
            for (SlimParser.TypeTypeContext ttc : tcl.typeType()) {
                SlimType type = annotatedTree.getType(ttc);
                functionType.addParamType(type);
            }
        }
    }

//    @Override
//    public void exitFunctionCall() {
//
//    }


    @Override
    public void exitFunctionDeclaration(SlimParser.FunctionDeclarationContext ctx) {
        Function function = (Function) annotatedTree.getScope(ctx);
        if (ctx.typeTypeOrVoid() != null) {
            // 设置函数的返回值类型
            function.setReturnType(annotatedTree.getType(ctx.typeTypeOrVoid()));
        }

        // 函数重定义检查
        Scope scope = annotatedTree.getEnclosingScope(ctx);
        Function found = scope.getFunction(function.getName(), function.paramTypes());
        if (found != null && found != function) {
            annotatedTree.log("Function or method already Declared: " + ctx.getText(), ctx);
        }

    }

    // 设置函数的参数的类型，这些参数已经在enterVariableDeclaratorId中作为变量声明了，现在设置它们的类型
    @Override
    public void exitFormalParameter(SlimParser.FormalParameterContext ctx) {
        // 设置参数类型
        SlimType type = annotatedTree.getType(ctx.typeType());
        Variable var = (Variable) annotatedTree.getSymbol(ctx.variableDeclaratorId());
        var.setType(type);

        // 添加到函数的参数列表里
        Scope scope = annotatedTree.getEnclosingScope(ctx);
        if (scope instanceof Function) {
            ((Function) scope).addParam(var);
        }
    }

    @Override
    public void exitTypeTypeOrVoid(SlimParser.TypeTypeOrVoidContext ctx) {
        if (ctx.VOID() != null) {
            annotatedTree.relateTypeToNode(VoidType.getInstance(), ctx);
        } else if (ctx.typeType() != null) {
            annotatedTree.relateTypeToNode(annotatedTree.getType(ctx.typeType()), ctx);
        }
    }

    @Override
    public void exitTypeType(SlimParser.TypeTypeContext ctx) {
        //        冒泡，将下级的属性标注在本级 ()
        //        typeType
        //        : (classOrInterfaceType| functionType | primitiveType) ('[' ']')*
        //        ;
        if (ctx.primitiveType() != null) {
            SlimType type = annotatedTree.getType(ctx.primitiveType());
            annotatedTree.relateTypeToNode(type, ctx);
        } else if (ctx.functionType() != null) {
            SlimType type = annotatedTree.getType(ctx.functionType());
            annotatedTree.relateTypeToNode(type, ctx);
        }

    }

    @Override
    public void exitPrimitiveType(SlimParser.PrimitiveTypeContext ctx) {
        SlimType type = null;
        if (ctx.BOOLEAN() != null) {
            type = PrimitiveType.Boolean;
        } else if (ctx.INT() != null) {
            type = PrimitiveType.Integer;
        } else if (ctx.LONG() != null) {
            type = PrimitiveType.Long;
        } else if (ctx.FLOAT() != null) {
            type = PrimitiveType.Float;
        } else if (ctx.DOUBLE() != null) {
            type = PrimitiveType.Double;
        } else if (ctx.BYTE() != null) {
            type = PrimitiveType.Byte;
        } else if (ctx.SHORT() != null) {
            type = PrimitiveType.Short;
        } else if (ctx.CHAR() != null) {
            type = PrimitiveType.Char;
        } else if (ctx.STRING() != null) {
            type = PrimitiveType.String;
        }

        annotatedTree.relateTypeToNode(type, ctx);
    }

}