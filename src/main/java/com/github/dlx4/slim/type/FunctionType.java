package com.github.dlx4.slim.type;

import java.util.List;

/**
 * @program: slim
 * @description: 函数（既是作用域又是类型，由于java不能多继承，因此函数类型改造成接口）
 * @author: dlx
 * @created: 2020/06/30 00:30
 */
public interface FunctionType extends SlimType {

    /**
     * @param function
     * @param paramTypes
     * @Description: 参数类型是否匹配
     * @return: boolean
     * @Creator: dlx
     */
    static boolean matchParameterTypes(FunctionType function, List<SlimType> paramTypes) {
        // 比较每个参数
        if (paramTypes.size() != paramTypes.size()) {
            return false;
        }

        boolean match = true;
        for (int i = 0; i < paramTypes.size(); i++) {
            SlimType type1 = function.paramTypes().get(i);
            SlimType type2 = paramTypes.get(i);
            if (!type1.is(type2)) {
                match = false;
                break;
            }
        }

        return match;
    }

    // 函数返回值类型
    SlimType returnType();

    // 函数参数类型
    List<SlimType> paramTypes();

    // 函数参数类型是否匹配
    boolean isMatch(List<SlimType> paramTypes);

}