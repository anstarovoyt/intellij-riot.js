package intellij.riot.xml

import com.intellij.lang.html.HTMLLanguage
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.html.HtmlDocumentImpl
import com.intellij.psi.impl.source.xml.XmlElementDescriptorProvider
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.XmlElementDescriptor
import intellij.riot.lang.RiotLanguage

class RiotComponentDescriptorProvider : XmlElementDescriptorProvider {
    override fun getDescriptor(tag: XmlTag?): XmlElementDescriptor? {
        if (tag == null) return null
        val language = tag.containingFile?.language
        if (language == RiotLanguage.INSTANCE) {
            val parent = tag.parent
            //only top tags
            if (parent is PsiFile || parent is HtmlDocumentImpl) {
                return RiotComponentDeclarationXmlDescriptor(tag)
            }
        }
        if (language == HTMLLanguage.INSTANCE) {
            val name = tag.name
            val componentTag = RiotComponentIndex.findComponent(name, tag) ?: return null
            return RiotComponentXmlDescriptor(componentTag, name)
        }

        return null
    }
}