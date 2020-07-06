package com.github.dlx4.slim.type;

/**
 * @program: slim
 * @description: void类型
 * @author: dlx
 * @created: 2020/06/30 00:34
 */
public class VoidType extends AbstractSlimType {

    // 单例
    private static VoidType voidType = new VoidType("Void");

    private VoidType(String name) {
        super(name);
    }

    public static VoidType getInstance() {
        return voidType;
    }

}