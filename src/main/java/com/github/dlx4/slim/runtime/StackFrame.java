package com.github.dlx4.slim.runtime;

import com.github.dlx4.slim.symbol.Scope;

/**
 * @program: slim
 * @description: 栈帧
 * @author: dlx
 * @created: 2020/06/30 19:51
 */
public class StackFrame {

    // frame对应的scope
    private Scope scope;
    // parent scope所对应的frame
    private StackFrame parentFrame;

}