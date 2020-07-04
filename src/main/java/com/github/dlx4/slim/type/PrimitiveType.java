package com.github.dlx4.slim.type;

/**
 * @program: slim
 * @description: 基本类型
 * @author: dlx
 * @created: 2020/06/30 00:37
 */
public class PrimitiveType extends SlimType {

    // 把常见的基础数据类型都定义出来
    public static PrimitiveType Integer = new PrimitiveType("Integer");
    public static PrimitiveType Long = new PrimitiveType("Long");
    public static PrimitiveType Float = new PrimitiveType("Float");
    public static PrimitiveType Double = new PrimitiveType("Double");
    public static PrimitiveType Boolean = new PrimitiveType("Boolean");
    public static PrimitiveType Byte = new PrimitiveType("Byte");
    public static PrimitiveType Char = new PrimitiveType("Char");
    public static PrimitiveType Short = new PrimitiveType("Short");
    public static PrimitiveType String = new PrimitiveType("Short"); //增加String为基础类型
    public static PrimitiveType Null = new PrimitiveType("null");

    private PrimitiveType(String name) {
        super(name);
    }

    /**
     * 计算两个类型中比较“高”的一级，比如int和long相加，要取long
     *
     * @param type1
     * @param type2
     * @return
     */
    public static PrimitiveType getUpperType(SlimType type1, SlimType type2) {
        PrimitiveType type;
        if (type1 == PrimitiveType.String || type2 == PrimitiveType.String) {
            type = PrimitiveType.String;
        } else if (type1 == PrimitiveType.Double || type2 == PrimitiveType.Double) {
            type = PrimitiveType.Double;
        } else if (type1 == PrimitiveType.Float || type2 == PrimitiveType.Float) {
            type = PrimitiveType.Float;
        } else if (type1 == PrimitiveType.Long || type2 == PrimitiveType.Long) {
            type = PrimitiveType.Long;
        } else if (type1 == PrimitiveType.Integer || type2 == PrimitiveType.Integer) {
            type = PrimitiveType.Integer;
        } else if (type1 == PrimitiveType.Short || type2 == PrimitiveType.Short) {
            type = PrimitiveType.Short;
        } else {
            type = PrimitiveType.Byte;
        }
        return type;
    }

    /**
     * 是否是数值型
     *
     * @param type
     * @return
     */
    public static boolean isNumeric(SlimType type) {
        if (type == PrimitiveType.Byte ||
                type == PrimitiveType.Short ||
                type == PrimitiveType.Integer ||
                type == PrimitiveType.Long ||
                type == PrimitiveType.Float ||
                type == PrimitiveType.Double) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }

}