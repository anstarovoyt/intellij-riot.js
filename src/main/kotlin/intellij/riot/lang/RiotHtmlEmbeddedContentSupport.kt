package intellij.riot.lang

import com.intellij.html.embedding.HtmlEmbeddedContentProvider
import com.intellij.html.embedding.HtmlEmbeddedContentSupport
import com.intellij.html.embedding.HtmlEmbedmentInfo
import com.intellij.lang.Language
import com.intellij.lang.javascript.JavaScriptSupportLoader
import com.intellij.lexer.BaseHtmlLexer
import com.intellij.lexer.HtmlScriptStyleEmbeddedContentProvider

class RiotHtmlEmbeddedContentSupport : HtmlEmbeddedContentSupport {

    override fun isEnabled(lexer: BaseHtmlLexer): Boolean = lexer is RiotHtmlLexer

    override fun createEmbeddedContentProviders(lexer: BaseHtmlLexer): List<HtmlEmbeddedContentProvider> {
        return listOf(RiotHtmlScriptStyleEmbeddedContentProvider(lexer))
    }

    class RiotHtmlScriptStyleEmbeddedContentProvider(lexer: BaseHtmlLexer) : HtmlScriptStyleEmbeddedContentProvider(lexer) {

        override fun styleLanguage(styleLang: String?): Language? =
                (if (styleLang != null && !styleLang.startsWith("text/")) {
                    Language.findLanguageByID(styleLang.toUpperCase())
                } else null) ?: super.styleLanguage(styleLang)

        override fun scriptEmbedmentInfo(mimeType: String?): HtmlEmbedmentInfo? =
                when (mimeType) {
                    "ts" -> HtmlEmbeddedContentSupport.getScriptTagEmbedmentInfo(JavaScriptSupportLoader.TYPESCRIPT)
                    "tsx" -> HtmlEmbeddedContentSupport.getScriptTagEmbedmentInfo(JavaScriptSupportLoader.TYPESCRIPT_JSX)
                    else -> super.scriptEmbedmentInfo(mimeType)
                }
    }

}