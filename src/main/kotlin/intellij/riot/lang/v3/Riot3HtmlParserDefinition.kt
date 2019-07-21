package intellij.riot.lang.v3

import com.intellij.lang.html.HTMLParserDefinition
import com.intellij.lang.javascript.types.JSFileElementType
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.html.HtmlFileImpl
import com.intellij.psi.stubs.PsiFileStub
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.IStubFileElementType

class Riot3HtmlParserDefinition : HTMLParserDefinition() {
    override fun getFileNodeType(): IFileElementType {
        return RIOT_FILE
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return HtmlFileImpl(viewProvider, RIOT_FILE)
    }

    override fun createLexer(project: Project?): Lexer {
        return Riot3Lexer()
    }

    companion object {
        internal var RIOT_FILE: IFileElementType = object : IStubFileElementType<PsiFileStub<HtmlFileImpl>>(Riot3HtmlLanguage.INSTANCE) {
            override fun getStubVersion(): Int {
                return super.getStubVersion() + JSFileElementType.getVersion()
            }
        }
    }
}