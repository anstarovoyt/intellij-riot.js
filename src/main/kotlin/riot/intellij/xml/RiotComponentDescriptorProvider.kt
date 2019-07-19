package riot.intellij.xml

import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.html.HtmlDocumentImpl
import com.intellij.psi.impl.source.xml.XmlElementDescriptorProvider
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.XmlElementDescriptor
import riot.intellij.lang.RiotLanguage

class RiotComponentDescriptorProvider : XmlElementDescriptorProvider {
    override fun getDescriptor(tag: XmlTag?): XmlElementDescriptor? {
        if (tag?.containingFile?.language == RiotLanguage.INSTANCE) {
            val parent = tag.parent
            //only top tags
            if (parent is PsiFile || parent is HtmlDocumentImpl) {
                return RiotComponentDeclarationXmlDescriptor(tag)
            }
        }

        return null
    }
}