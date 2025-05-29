package intellij.riot.js.v3

import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.lang.ecmascript6.parsing.ES6Parser
import com.intellij.lang.ecmascript6.parsing.ES6StatementParser
import com.intellij.lang.javascript.JSTokenTypes
import com.intellij.lang.javascript.dialects.ECMA6ParserDefinition
import com.intellij.lang.javascript.parsing.FunctionParser
import com.intellij.lang.javascript.parsing.JavaScriptParser
import com.intellij.lang.javascript.types.JSFileElementType
import com.intellij.openapi.project.Project
import com.intellij.psi.tree.IFileElementType

/**
 * @see Riot3TagJSLanguage
 */
class Riot3TagJSParsingDefinition : ECMA6ParserDefinition() {
    companion object {
        private val FILE: IFileElementType = JSFileElementType.create(Riot3TagJSLanguage.INSTANCE)
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createJSParser(builder: PsiBuilder): JavaScriptParser {
        return Riot3TagJSParser(builder)
    }

    override fun createParser(project: Project?): PsiParser {
        return PsiParser { root, builder ->
            Riot3TagJSParser(builder).parseJS(root)
            return@PsiParser builder.treeBuilt
        }
    }
}

class Riot3TagJSParser(builder: PsiBuilder) : ES6Parser(builder) {
    override val statementParser: ES6StatementParser<*> by lazy {
        Riot3StatementParser(this)
    }
}

class Riot3StatementParser(parser: ES6Parser) : ES6StatementParser<ES6Parser>(parser) {
    override fun parseStatement() {
        val isAsync = JSTokenTypes.ASYNC_KEYWORD == builder.tokenType
        if (isIdentifierToken(builder.tokenType) || isAsync && isIdentifierToken(builder.tokenType)) {
            val lineCandidate = builder.lookAhead(if (isAsync) 2 else 1)
            if (lineCandidate == JSTokenTypes.LPAR) {
                val mark = builder.mark()

                val functionParser = parser.functionParser

                if (functionParser.parseAttributesList() && functionParser.parseFunctionNoMarker(
                        FunctionParser.Context.SOURCE_ELEMENT,
                        mark
                    )
                ) {
                    return
                }
                mark.rollbackTo()
            }
        }

        super.parseStatement()
    }
}