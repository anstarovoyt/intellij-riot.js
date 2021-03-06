package intellij.riot.js

import com.intellij.codeInsight.daemon.ImplicitUsageProvider
import com.intellij.lang.ecmascript6.psi.ES6ExportDefaultAssignment
import com.intellij.psi.PsiElement
import intellij.riot.lang.IRiotFileType

/**
 * Avoid "unused" for top-level export default {} in riot templates
 */
class RiotImplicitUsageProvider : ImplicitUsageProvider {
    override fun isImplicitUsage(psiElement: PsiElement): Boolean {
        return isImplicitRead(psiElement)
    }

    override fun isImplicitRead(psiElement: PsiElement): Boolean {
        if (psiElement !is ES6ExportDefaultAssignment) return false


        return psiElement.containingFile.fileType is IRiotFileType
    }

    override fun isImplicitWrite(psiElement: PsiElement): Boolean {
        return false
    }
}
