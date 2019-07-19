package riot.intellij.js

import com.intellij.lang.PsiParser
import com.intellij.lang.javascript.JavascriptParserDefinition
import com.intellij.lang.javascript.types.JSFileElementType
import com.intellij.openapi.project.Project
import com.intellij.psi.tree.IFileElementType

class RiotJSParsingDefinition : JavascriptParserDefinition() {
    companion object {
        private val FILE: IFileElementType = JSFileElementType.create(RiotJSLanguage.INSTANCE)
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createParser(project: Project?): PsiParser {
        return PsiParser { root, builder ->
            RiotJavaScriptLanguageParser(builder).parseRiot(root)
            return@PsiParser builder.treeBuilt
        }
    }
}