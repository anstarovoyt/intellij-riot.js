package intellij.riot.lang

import com.intellij.ide.highlighter.HtmlFileHighlighter
import com.intellij.lexer.Lexer
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class RiotHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(p0: Project?, p1: VirtualFile?): SyntaxHighlighter = RiotHtmlHighlighter()
}

class RiotHtmlHighlighter : HtmlFileHighlighter() {
    override fun getHighlightingLexer(): Lexer {
        return RiotHtmlHighlightingLexer()
    }
}