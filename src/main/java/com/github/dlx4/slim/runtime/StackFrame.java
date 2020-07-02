package com.github.dlx4.slim.runtime;

import com.github.dlx4.slim.symbol.BlockScope;
import com.github.dlx4.slim.symbol.Scope;
import com.github.dlx4.slim.symbol.Variable;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: slim
 * @description: 栈帧
 * @author: dlx
 * @created: 2020/06/30 19:51
 */
@Getter
@Setter
public class StackFrame {

    // frame对应的scope
    private final Scope scope;
    // parent scope所对应的frame
    private StackFrame parentFrame;
    // 变量存储的store
    private final RtStore rtStore;

    public StackFrame(BlockScope scope) {
        this.scope = scope;
        this.rtStore = new RtStore();
    }

    /**
     * 是否包含某个变量
     *
     * @param variable
     * @return
     */
    public boolean contains(Variable variable) {
        if (rtStore != null) {
            return rtStore.contains(variable);
        }
        return false;
    }

    @Override
    public String toString() {
        String ret = "" + scope;
        if (parentFrame != null) {
            ret += " -> " + parentFrame;
        }
        return ret;
    }
}