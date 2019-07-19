package riot.intellij.js

import com.intellij.lang.PsiBuilder
import com.intellij.lang.ecmascript6.parsing.ES6ExpressionParser
import com.intellij.lang.ecmascript6.parsing.ES6FunctionParser
import com.intellij.lang.ecmascript6.parsing.ES6Parser
import com.intellij.lang.ecmascript6.parsing.ES6StatementParser
import com.intellij.lang.javascript.parsing.JSPsiTypeParser
import com.intellij.lang.javascript.parsing.JavaScriptParser
import com.intellij.psi.tree.IElementType

class RiotJavaScriptLanguageParser(builder: PsiBuilder) : ES6Parser<ES6ExpressionParser<*>, ES6StatementParser<*>,
        ES6FunctionParser<*>, JSPsiTypeParser<JavaScriptParser<*, *, *, *>>>(builder) {
    init {
        myStatementParser = object : ES6StatementParser<RiotJavaScriptLanguageParser>(this) {
            override fun parseSourceElement() {
                super.parseSourceElement()
            }
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