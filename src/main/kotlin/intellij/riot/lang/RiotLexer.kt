package intellij.riot.lang

import com.intellij.lang.javascript.JSElementTypes
import com.intellij.lexer.*
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.tree.IElementType
import com.intellij.psi.xml.XmlTokenType
import intellij.riot.js.RiotJSLanguage
import intellij.riot.xml._RiotHtmlLexer


class RiotLexer : HtmlLexer(FlexAdapter(_RiotHtmlLexer()), true) {
    override fun getTokenType(): IElementType? {
        val tokenType = super.getTokenType()
        if (seenAttribute && tokenType == JSElementTypes.EMBEDDED_CONTENT) {
            return RiotJSLanguage.EMBEDDED_JS
        }
        return tokenType
    }
}