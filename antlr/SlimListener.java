// Generated from Slim.g4 by ANTLR 4.7.2



import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SlimParser}.
 */
public interface SlimListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SlimParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(SlimParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(SlimParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(SlimParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(SlimParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(SlimParser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(SlimParser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclaration(SlimParser.MemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclaration(SlimParser.MemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(SlimParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(SlimParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(SlimParser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(SlimParser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 */
	void enterTypeTypeOrVoid(SlimParser.TypeTypeOrVoidContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 */
	void exitTypeTypeOrVoid(SlimParser.TypeTypeOrVoidContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNameList(SlimParser.QualifiedNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNameList(SlimParser.QualifiedNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(SlimParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(SlimParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(SlimParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(SlimParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(SlimParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(SlimParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void enterLastFormalParameter(SlimParser.LastFormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void exitLastFormalParameter(SlimParser.LastFormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(SlimParser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(SlimParser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(SlimParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(SlimParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(SlimParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(SlimParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(SlimParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(SlimParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarators(SlimParser.VariableDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarators(SlimParser.VariableDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(SlimParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(SlimParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(SlimParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(SlimParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(SlimParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(SlimParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(SlimParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(SlimParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceType(SlimParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceType(SlimParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(SlimParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(SlimParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(SlimParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(SlimParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(SlimParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(SlimParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteral(SlimParser.FloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteral(SlimParser.FloatLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(SlimParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(SlimParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SlimParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SlimParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatements(SlimParser.BlockStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatements(SlimParser.BlockStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(SlimParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(SlimParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SlimParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SlimParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(SlimParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(SlimParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(SlimParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(SlimParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#forControl}.
	 * @param ctx the parse tree
	 */
	void enterForControl(SlimParser.ForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#forControl}.
	 * @param ctx the parse tree
	 */
	void exitForControl(SlimParser.ForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(SlimParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(SlimParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForControl(SlimParser.EnhancedForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForControl(SlimParser.EnhancedForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(SlimParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(SlimParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(SlimParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(SlimParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(SlimParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(SlimParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SlimParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SlimParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(SlimParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(SlimParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(SlimParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(SlimParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#typeType}.
	 * @param ctx the parse tree
	 */
	void enterTypeType(SlimParser.TypeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#typeType}.
	 * @param ctx the parse tree
	 */
	void exitTypeType(SlimParser.TypeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#functionType}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(SlimParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#functionType}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(SlimParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(SlimParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(SlimParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(SlimParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(SlimParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void enterSuperSuffix(SlimParser.SuperSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void exitSuperSuffix(SlimParser.SuperSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link SlimParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(SlimParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SlimParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(SlimParser.ArgumentsContext ctx);
}