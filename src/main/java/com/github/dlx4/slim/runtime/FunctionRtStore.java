package com.github.dlx4.slim.runtime;

import com.github.dlx4.slim.symbol.Function;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: slim
 * @description: 函数运行时本地变量的值
 * @author: dlx
 * @created: 2020/07/05 00:39
 */
@Setter
@Getter
public class FunctionRtStore extends RtStore {

    // 函数类型
    private Function function;

    public FunctionRtStore(Function function) {
        this.function = function;
    }
}