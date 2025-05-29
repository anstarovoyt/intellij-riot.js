package intellij.riot.js

import com.intellij.lang.PsiBuilder
import com.intellij.lang.ecmascript6.parsing.ES6Parser
import com.intellij.psi.tree.IElementType

class RiotJavaScriptLanguageParser(builder: PsiBuilder) : ES6Parser(builder) {

    override fun doParseJS() {
        while (!builder.eof()) {
            if (!expressionParser.parseAssignmentExpression(true)) {
                builder.advanceLexer()
            }
        }
    }

    fun parseRiot(elementType: IElementType) {
        return parseJS(elementType)
    }
}