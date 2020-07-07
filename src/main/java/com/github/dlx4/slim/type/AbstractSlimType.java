package com.github.dlx4.slim.type;

import com.github.dlx4.slim.symbol.Scope;
import lombok.Getter;

/**
 * @program: slim
 * @description: 类型
 * @author: dlx
 * @created: 2020/06/30 00:32
 */
@Getter
public abstract class AbstractSlimType implements SlimType {

    protected String name;
    protected Scope enclosingScope;

    public AbstractSlimType(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        if (name == null) {
            return "";
        } else {
            return this.name;
        }
    }
}