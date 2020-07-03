package com.github.dlx4.slim.runtime;

import com.github.dlx4.slim.symbol.Variable;

/**
 * @program: slim
 * @description: 左值
 * @author: dlx
 * @created: 2020/07/01 23:33
 */
public class LeftValue {

    private final Variable variable;
    private final RtStore rtStore;

    public LeftValue(RtStore store, Variable variable) {
        this.rtStore = store;
        this.variable = variable;
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

    /**
     * @param
     * @Description: 左值get
     * @return: java.lang.Object
     * @Creator: dlx
     */
    public Object getValue() {
        return rtStore.getValue(variable);
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}