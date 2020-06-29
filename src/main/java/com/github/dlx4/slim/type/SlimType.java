package com.github.dlx4.slim.type;

import com.github.dlx4.slim.symbol.Scope;

/**
 * @program: slim
 * @description: 类型
 * @author: dlx
 * @created: 2020/06/30 00:32
 */
public interface SlimType {

    String getName();

    Scope getEnclosingScope();

    /**
     * @param type
     * @Description: 本类型是不是 is 目标类型
     * @return: boolean
     * @Creator: dlx
     */
    boolean isType(SlimType type);
}