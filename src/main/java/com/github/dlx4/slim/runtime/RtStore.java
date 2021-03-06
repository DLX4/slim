package com.github.dlx4.slim.runtime;

import com.github.dlx4.slim.symbol.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: slim
 * @description: 运行时数据对象
 * @author: dlx
 * @created: 2020/07/02 23:07
 */
public class RtStore {

    // 成员变量
    private Map<Variable, Object> store = new HashMap<>();


    /**
     * @param variable
     * @Description: 是否包含
     * @return: boolean
     * @Creator: dlx
     */
    public boolean contains(Variable variable) {
        return store.containsKey(variable);
    }

    /**
     * @param variable
     * @Description: 获取变量运行时的值
     * @return: java.lang.Object
     * @Creator: dlx
     */
    public Object getValue(Variable variable) {
        Object ret = store.get(variable);

        return ret;
    }

    /**
     * @param variable  @param value
     * @Description: 设置变量运行时的值
     * @return: void
     * @Creator: dlx
     */
    public void setValue(Variable variable, Object value) {
        store.put(variable, value);
    }
}