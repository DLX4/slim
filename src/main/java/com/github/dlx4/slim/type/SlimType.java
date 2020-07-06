package com.github.dlx4.slim.type;

/**
 * @program: slim
 * @description: 类型
 * @author: dlx
 * @created: 2020/07/06 23:00
 */
public interface SlimType {

    default boolean is(SlimType type) {
        return this == type;
    }
}