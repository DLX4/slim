package com.github.dlx4.slim.symbol;

import com.github.dlx4.slim.type.FunctionType;
import com.github.dlx4.slim.type.SlimType;
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
public class Function extends Scope implements FunctionType {

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
}