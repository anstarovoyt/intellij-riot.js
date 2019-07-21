package intellij.riot.xml

import com.intellij.psi.PsiFile
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.HtmlXmlExtension
import intellij.riot.lang.IRiotFileType

class RiotXmlExtension : HtmlXmlExtension() {
    override fun isAvailable(p0: PsiFile?): Boolean = p0?.fileType is IRiotFileType
    override fun isCollapsibleTag(tag: XmlTag): Boolean = true
}