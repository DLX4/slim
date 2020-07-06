package com.github.dlx4.slim.type;

import com.github.dlx4.slim.SlimUtils;
import com.github.dlx4.slim.symbol.Scope;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: slim
 * @description: 只有函数类型的定义
 * @author: dlx
 * @created: 2020/07/04 15:30
 */
@Setter
@Getter
public class DefaultFunctionType extends AbstractSlimType implements FunctionType {

    private final String name = null;
    private final List<SlimType> paramTypes = new LinkedList<>();
    private Scope enclosingScope = null;
    private SlimType returnType = null;

    public DefaultFunctionType() {
        super(SlimUtils.generateSymbolName(FunctionType.class));
    }

    public void addParamType(SlimType type) {
        this.paramTypes.add(type);
    }

    @Override
    public SlimType returnType() {
        return returnType;
    }

    @Override
    public List<SlimType> paramTypes() {
        return paramTypes;
    }

    @Override
    public boolean isMatch(List<SlimType> paramTypes) {
        return FunctionType.matchParameterTypes(this, paramTypes);
    }
}