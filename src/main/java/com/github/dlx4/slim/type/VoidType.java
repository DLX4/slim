package com.github.dlx4.slim.type;

import com.github.dlx4.slim.symbol.Scope;

/**
 * @program: slim
 * @description: void类型
 * @author: dlx
 * @created: 2020/06/30 00:34
 */
public class VoidType implements SlimType {

    // 单例
    private static VoidType voidType = new VoidType();

    private VoidType() {
    }

    public static VoidType getInstance() {
        return voidType;
    }

    @Override
    public String getName() {
        return "void";
    }

    @Override
    public Scope getEnclosingScope() {
        return null;
    }

    @Override
    public boolean isType(SlimType type) {
        return this == type;
    }

    @Override
    public String toString() {
        return "void";
    }
}