package intellij.riot.js.v3

import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.lang.ecmascript6.parsing.ES6ExpressionParser
import com.intellij.lang.ecmascript6.parsing.ES6FunctionParser
import com.intellij.lang.ecmascript6.parsing.ES6Parser
import com.intellij.lang.ecmascript6.parsing.ES6StatementParser
import com.intellij.lang.javascript.JSTokenTypes
import com.intellij.lang.javascript.dialects.ECMA6ParserDefinition
import com.intellij.lang.javascript.parsing.FunctionParser
import com.intellij.lang.javascript.parsing.JSPsiTypeParser
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

    override fun createParser(project: Project?): PsiParser {
        return PsiParser { root, builder ->
            Riot3TagJSParser(builder).parseJS(root)
            return@PsiParser builder.treeBuilt
        }
    }
}

class Riot3TagJSParser(builder: PsiBuilder) : ES6Parser<ES6ExpressionParser<*>, ES6StatementParser<*>,
        ES6FunctionParser<*>, JSPsiTypeParser<JavaScriptParser<*, *, *, *>>>(builder) {
    init {
        myStatementParser = object : ES6StatementParser<Riot3TagJSParser>(this) {
            override fun parseSourceElement() {
                if (isIdentifierToken(builder.tokenType)) {
                    val next = builder.lookAhead(1)
                    if (next == JSTokenTypes.LPAR) {
                        val mark = builder.mark()
                        if (myFunctionParser.parseFunctionNoMarker(FunctionParser.Context.SOURCE_ELEMENT, mark)) {
                            return
                        }
                        mark.rollbackTo()
                    }
                }

                super.parseSourceElement()
            }
        }
    }
}