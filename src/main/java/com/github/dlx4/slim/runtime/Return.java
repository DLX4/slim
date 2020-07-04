package com.github.dlx4.slim.runtime;

import lombok.Builder;

/**
 * @program: slim
 * @description: 表示return语句
 * @author: dlx
 * @created: 2020/07/04 00:11
 */
@Builder
public class Return {
    private Object value;

    @Override
    public String toString() {
        if (value != null) {
            return value.toString();
        } else {
            return "null";
        }
    }
}