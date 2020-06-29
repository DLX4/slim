package com.github.dlx4.slim.type;

import java.util.List;

/**
 * @program: slim
 * @description: 函数类型
 * @author: dlx
 * @created: 2020/06/30 00:30
 */
public interface FunctionType {

    SlimType getReturnType();

    List<SlimType> getParamTypes();

    boolean matchParameterTypes(List<SlimType> paramTypes);

}