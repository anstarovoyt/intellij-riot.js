package intellij.riot.lang

import com.intellij.html.embedding.HtmlEmbeddedContentProvider
import com.intellij.javascript.JSHtmlEmbeddedContentSupport
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.HtmlHighlightingLexer
import com.intellij.lexer.HtmlScriptStyleEmbeddedContentProvider
import com.intellij.lexer.MergingLexerAdapter
import intellij.riot.xml._RiotHtmlLexer

class RiotHtmlHighlightingLexer : HtmlHighlightingLexer(MergingLexerAdapter(FlexAdapter(_RiotHtmlLexer()), TOKENS_TO_MERGE), true, null) {
    override fun acceptEmbeddedContentProvider(provider: HtmlEmbeddedContentProvider): Boolean =
            provider !is JSHtmlEmbeddedContentSupport.JSInlineEmbeddedContentProvider
                    && provider::class != HtmlScriptStyleEmbeddedContentProvider::class
}