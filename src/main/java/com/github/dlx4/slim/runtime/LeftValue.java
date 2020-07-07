package com.github.dlx4.slim.runtime;

import com.github.dlx4.slim.symbol.Variable;
import lombok.Builder;

/**
 * @program: slim
 * @description: 左值
 * @author: dlx
 * @created: 2020/07/01 23:33
 */
@Builder
public class LeftValue {

    private final Variable variable;
    private final RtStore rtStore;

//    public LeftValue(RtStore store, Variable variable) {
//        this.rtStore = store;
//        this.variable = variable;
//    }

    /**
     * @param
     * @Description: 左值get
     * @return: java.lang.Object
     * @Creator: dlx
     */
    public Object getValue() {
        return rtStore.getValue(variable);
    }

    /**
     * @param value
     * @Description: 左值set
     * @return: void
     * @Creator: dlx
     */
    public void setValue(Object value) {
        rtStore.setValue(variable, value);
    }

    @Override
    public String toString() {
        if (variable == null) {
            return "unknown variable";
        } else {
            return variable.getName();
        }
    }
}