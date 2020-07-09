package com.github.dlx4.slim.symbol;

import com.github.dlx4.slim.SlimUtils;
import com.github.dlx4.slim.runtime.LeftValue;
import com.github.dlx4.slim.runtime.RtStack;
import com.github.dlx4.slim.runtime.RtStore;
import com.github.dlx4.slim.type.FunctionType;
import com.github.dlx4.slim.type.SlimType;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @program: slim
 * @description: 函数实例
 * @author: dlx
 * @created: 2020/07/04 13:34
 */
@Setter
@Getter
public class Function extends BlockScope implements FunctionType {

    // 参数
    private final List<Variable> paramVars = new LinkedList<>();
    // 参数类型
    private final List<SlimType> paramTypes = new LinkedList<>();
    // 返回值
    private SlimType returnType;
    // 闭包变量
    private Set<Variable> closureVars;

    public Function(String name, Scope enclosingScope, ParserRuleContext ctx) {
        super(name, enclosingScope, ctx);
    }

    /**
     * @param
     * @Description: 添加一个function参数
     * @return: void
     * @Creator: dlx
     */
    public void addParam(Variable var) {
        this.paramVars.add(var);
        this.paramTypes.add(var.getType());
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

    @Override
    public boolean isMatchType(SlimType type) {
        if (type instanceof FunctionType) {
            return isMatch(((FunctionType) type).paramTypes());
        }

        return false;
    }


}