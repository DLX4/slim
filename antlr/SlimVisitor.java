// Generated from Slim.g4 by ANTLR 4.7.2



import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SlimParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SlimVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SlimParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(SlimParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(SlimParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBodyDeclaration(SlimParser.ClassBodyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#memberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDeclaration(SlimParser.MemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(SlimParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#functionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBody(SlimParser.FunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeTypeOrVoid(SlimParser.TypeTypeOrVoidContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedNameList(SlimParser.QualifiedNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(SlimParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(SlimParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(SlimParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLastFormalParameter(SlimParser.LastFormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#variableModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableModifier(SlimParser.VariableModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(SlimParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(SlimParser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(SlimParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#variableDeclarators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarators(SlimParser.VariableDeclaratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(SlimParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorId(SlimParser.VariableDeclaratorIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(SlimParser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(SlimParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassOrInterfaceType(SlimParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#typeArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgument(SlimParser.TypeArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(SlimParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#integerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(SlimParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#floatLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteral(SlimParser.FloatLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(SlimParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SlimParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#blockStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatements(SlimParser.BlockStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(SlimParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SlimParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchBlockStatementGroup(SlimParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#switchLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchLabel(SlimParser.SwitchLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#forControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForControl(SlimParser.ForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(SlimParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#enhancedForControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnhancedForControl(SlimParser.EnhancedForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(SlimParser.ParExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(SlimParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(SlimParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SlimParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(SlimParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#typeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeList(SlimParser.TypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#typeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeType(SlimParser.TypeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#functionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionType(SlimParser.FunctionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(SlimParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreator(SlimParser.CreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#superSuffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperSuffix(SlimParser.SuperSuffixContext ctx);
	/**
	 * Visit a parse tree produced by {@link SlimParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(SlimParser.ArgumentsContext ctx);
}