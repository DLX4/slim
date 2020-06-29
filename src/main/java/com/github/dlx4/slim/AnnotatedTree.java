package com.github.dlx4.slim;

import lombok.Data;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @program: slim
 * @description: 注释树（语义分析结果）
 * @author: dlx
 * @created: 2020/06/29 00:39
 */
@Data
public class AnnotatedTree {

    private ParseTree ast;
}