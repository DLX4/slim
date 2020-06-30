package com.github.dlx4.slim.scanner;

import com.github.dlx4.slim.AnnotatedTree;
import com.github.dlx4.slim.antlr.SlimParser;
import com.github.dlx4.slim.type.PrimitiveType;
import com.github.dlx4.slim.type.SlimType;
import com.github.dlx4.slim.type.VoidType;

/**
 * @program: slim
 * @description: 扫描变量
 * @author: dlx
 * @created: 2020/06/30 22:48
 */
public class VariableScanner extends AbstractAstScanner {


    public VariableScanner(AnnotatedTree annotatedTree) {
        super(annotatedTree);
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
        /*
        冒泡，将下级的属性标注在本级 ()
        typeType
        : (classOrInterfaceType| functionType | primitiveType) ('[' ']')*
        ;
        */
        if (ctx.primitiveType() != null) {
            SlimType type = annotatedTree.getType(ctx.primitiveType());
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