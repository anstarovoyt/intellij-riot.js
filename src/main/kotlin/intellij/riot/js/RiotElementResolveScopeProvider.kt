package intellij.riot.js

import com.intellij.lang.javascript.DialectDetector
import com.intellij.lang.javascript.ecmascript6.TypeScriptResolveScopeProvider
import com.intellij.lang.javascript.psi.resolve.JSElementResolveScopeProvider
import com.intellij.lang.javascript.psi.resolve.JSResolveScopeProvider
import com.intellij.lang.typescript.library.TypeScriptLibraryProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.search.GlobalSearchScope
import intellij.riot.lang.RiotHtmlFileType

class RiotElementResolveScopeProvider : JSElementResolveScopeProvider {
    private val tsProvider = object : TypeScriptResolveScopeProvider() {
        override fun isApplicable(file: VirtualFile): Boolean = true

        override fun restrictByFileType(file: VirtualFile,
                                        libraryService: TypeScriptLibraryProvider,
                                        moduleAndLibraryScope: GlobalSearchScope): GlobalSearchScope {
            return super.restrictByFileType(file, libraryService, moduleAndLibraryScope).uniteWith(
                    GlobalSearchScope.getScopeRestrictedByFileTypes(moduleAndLibraryScope, file.fileType))
        }
    }

    override fun getElementResolveScope(element: PsiElement): GlobalSearchScope? {
        val psiFile = element.containingFile
        if (psiFile?.fileType !is RiotHtmlFileType) return null
        if (DialectDetector.isTypeScript(element)) {
            return tsProvider.getResolveScope(psiFile.viewProvider.virtualFile, element.project)
        }
        return null
    }
}
