package intellij.riot.lang

import com.intellij.lang.javascript.JSElementTypes
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.HtmlHighlightingLexer
import com.intellij.psi.tree.IElementType
import intellij.riot.js.RiotElements
import intellij.riot.xml._RiotHtmlLexer

class RiotHighlightingLexer : HtmlHighlightingLexer(FlexAdapter(_RiotHtmlLexer()), true, null) {
    override fun getTokenType(): IElementType? {
        val tokenType = super.getTokenType()
        if (seenAttribute && tokenType == JSElementTypes.EMBEDDED_CONTENT) {
            return RiotElements.EMBEDDED_JS
        }
        return tokenType
    }
} 