package intellij.riot.lang

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class RiotHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(p0: Project?, p1: VirtualFile?): SyntaxHighlighter = RiotHighlighter()
}