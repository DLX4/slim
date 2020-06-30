package com.github.dlx4.slim;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @program: slim
 * @description: 编译器日志
 * @author: dlx
 * @created: 2020/07/01 00:35
 */
@Data
@Builder
public class CompilationLog {

    // 类型，包括信息、警告、错误。
    public static int INFO = 0;
    public static int WARNING = 1;
    public static int ERROR = 2;

    private String message;
    private int line;
    private int positionInLine;
    // 相关的AST节点
    private ParserRuleContext ctx;
    private int type = INFO;

    @Override
    public String toString() {
        return message + " @" + line + ":" + positionInLine;
    }

}