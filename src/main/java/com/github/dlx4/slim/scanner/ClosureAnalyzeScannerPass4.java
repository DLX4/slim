package com.github.dlx4.slim.scanner;

import com.github.dlx4.slim.AnnotatedTree;
import com.github.dlx4.slim.SlimUtils;
import com.github.dlx4.slim.symbol.Function;
import com.github.dlx4.slim.symbol.Scope;
import com.github.dlx4.slim.symbol.SlimSymbol;
import com.github.dlx4.slim.symbol.Variable;
import com.github.dlx4.slim.type.SlimType;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.github.dlx4.slim.AnnotatedTree.isAncestor;

/**
 * @program: slim
 * @description: 闭包分析扫描
 * @author: dlx
 * @created: 2020/07/09 21:18
 */
public class ClosureAnalyzeScannerPass4 extends AbstractAstScanner {

    public ClosureAnalyzeScannerPass4(AnnotatedTree annotatedTree) {
        super(annotatedTree);
    }

    /**
     * @param
     * @Description: 闭包分析（设置闭包变量）
     * @return: void
     * @Creator: dlx
     */
    public void analyzeClosures() {
        for (SlimType type : annotatedTree.getFunctionTypes()) {
            if (type instanceof Function) {
                Set set = closureVariables((Function) type);
                if (!SlimUtils.isEmpty(set)) {
                    ((Function) type).setClosureVars(set);
                }
            }
        }
    }

    /**
     * 为某个函数计算闭包变量，也就是它所引用的外部环境变量。
     * 算法：计算所有的变量引用，去掉内部声明的变量，剩下的就是外部的。
     *
     * @param function
     * @return
     */
    private Set<Variable> closureVariables(Function function) {
        Set<Variable> referred = variablesReferredByScope(function);
        Set<Variable> declared = variablesDeclaredUnderScope(function);
        referred.removeAll(declared);
        return referred;
    }


    /**
     * 在一个Scope（及）下级Scope中声明的所有变量的集合
     *
     * @param scope
     * @return
     */
    private Set<Variable> variablesDeclaredUnderScope(Scope scope) {
        Set<Variable> rtn = new HashSet<>();
        for (SlimSymbol symbol : scope.getSymbols()) {
            if (symbol instanceof Variable) {
                rtn.add((Variable) symbol);
            } else if (symbol instanceof Scope) {
                rtn.addAll(variablesDeclaredUnderScope((Scope) symbol));
            }
        }

        return rtn;
    }

    /**
     * 被一个Scope（包括下级Scope）内部的代码所引用的所有变量的集合
     *
     * @param scope
     * @return
     */
    private Set<Variable> variablesReferredByScope(Scope scope) {
        Set<Variable> ret = new HashSet<>();

        ParserRuleContext scopeNode = scope.getCtx();

        // 扫面所有的符号引用。这对于大的程序性能不够优化，因为符号表太大。
        Map<ParserRuleContext, SlimSymbol> symbols = annotatedTree.getNodeRelateSymbol();
        for (ParserRuleContext node : symbols.keySet()) {
            SlimSymbol symbol = symbols.get(node);
            if (symbol instanceof Variable && isAncestor(scopeNode, node)) {
                ret.add((Variable) symbol);
            }
        }

        return ret;
    }
}