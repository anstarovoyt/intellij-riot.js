package intellij.riot.js

import com.intellij.lang.PsiBuilder
import com.intellij.lang.ecmascript6.parsing.ES6ExpressionParser
import com.intellij.lang.ecmascript6.parsing.ES6FunctionParser
import com.intellij.lang.ecmascript6.parsing.ES6Parser
import com.intellij.lang.ecmascript6.parsing.ES6StatementParser
import com.intellij.lang.javascript.JSTokenTypes
import com.intellij.lang.javascript.parsing.JSPsiTypeParser
import com.intellij.lang.javascript.parsing.JavaScriptParser
import com.intellij.psi.tree.IElementType

class RiotJavaScriptLanguageParser(builder: PsiBuilder) : ES6Parser<ES6ExpressionParser<*>, ES6StatementParser<*>,
        ES6FunctionParser<*>, JSPsiTypeParser<JavaScriptParser<*, *, *, *>>>(builder) {
    init {
        myStatementParser = object : ES6StatementParser<RiotJavaScriptLanguageParser>(this) {
            override fun parseSourceElement() {
                val tokenType = builder.tokenType
                
                val hasLBrace = tokenType == JSTokenTypes.LBRACE
                if (hasLBrace) builder.advanceLexer()
                myExpressionParser.parseExpression()
                
                if (hasLBrace && builder.tokenType == JSTokenTypes.RBRACE) {
                    builder.advanceLexer()
                } 
            }
        }
        myExpressionParser = object: ES6ExpressionParser<RiotJavaScriptLanguageParser>(this) {
            
        }
    }

    fun parseRiot(elementType: IElementType) {
        val rootMarker = builder.mark()
        while (!builder.eof()) {
            myStatementParser.parseSourceElement()
        }
        rootMarker.done(elementType)
    }
}