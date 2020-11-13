package intellij.riot.lang

import com.intellij.html.embedding.HtmlEmbeddedContentProvider
import com.intellij.javascript.JSHtmlEmbeddedContentSupport
import com.intellij.lang.HtmlScriptContentProvider
import com.intellij.lang.Language
import com.intellij.lang.LanguageHtmlScriptContentProvider
import com.intellij.lang.javascript.JSElementTypes
import com.intellij.lang.javascript.JavaScriptSupportLoader
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.HtmlLexer
import com.intellij.lexer.HtmlScriptStyleEmbeddedContentProvider
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

open class RiotHtmlLexer : HtmlLexer(MergingLexerAdapter(FlexAdapter(_RiotHtmlLexer()), TOKENS_TO_MERGE), true) {

    override fun acceptEmbeddedContentProvider(provider: HtmlEmbeddedContentProvider): Boolean =
            provider !is JSHtmlEmbeddedContentSupport.JSInlineEmbeddedContentProvider
                    && provider::class != HtmlScriptStyleEmbeddedContentProvider::class
}