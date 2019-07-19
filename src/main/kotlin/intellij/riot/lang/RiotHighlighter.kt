package intellij.riot.lang

import com.intellij.ide.highlighter.HtmlFileHighlighter
import com.intellij.lexer.Lexer

class RiotHighlighter: HtmlFileHighlighter() {
    override fun getHighlightingLexer(): Lexer {
        return RiotHighlightingLexer()
    }
}