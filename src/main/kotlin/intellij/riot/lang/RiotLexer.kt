package intellij.riot.lang

import com.intellij.lang.javascript.JSElementTypes
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.HtmlLexer
import com.intellij.lexer.MergingLexerAdapter
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.xml.XmlElementType
import com.intellij.psi.xml.XmlTokenType
import intellij.riot.xml._RiotHtmlLexer


val TOKENS_TO_MERGE by lazy {
    TokenSet.create(
            XmlTokenType.XML_COMMENT_CHARACTERS,
            XmlTokenType.XML_WHITE_SPACE,
            XmlTokenType.XML_REAL_WHITE_SPACE,
            XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN,
            XmlTokenType.XML_DATA_CHARACTERS,
            XmlTokenType.XML_TAG_CHARACTERS)
}

class RiotLexer : HtmlLexer(MergingLexerAdapter(FlexAdapter(_RiotHtmlLexer()), TOKENS_TO_MERGE), true) {
    override fun getTokenType(): IElementType? {
        val tokenType = super.getTokenType()
        if (seenAttribute && tokenType == JSElementTypes.EMBEDDED_CONTENT) {
            return XmlElementType.XML_ATTRIBUTE_VALUE_TOKEN
        }
        return tokenType
    }
}