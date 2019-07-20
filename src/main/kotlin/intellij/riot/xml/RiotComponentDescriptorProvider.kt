package intellij.riot.xml

import com.intellij.lang.html.HTMLLanguage
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.html.HtmlDocumentImpl
import com.intellij.psi.impl.source.xml.XmlElementDescriptorProvider
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.XmlElementDescriptor
import com.intellij.xml.XmlNSDescriptor
import intellij.riot.lang.RiotLanguage

class RiotComponentDescriptorProvider : XmlElementDescriptorProvider {
    override fun getDescriptor(tag: XmlTag?): XmlElementDescriptor? {
        if (tag == null) return null
        val name = tag.name
        val language = tag.containingFile?.language
        if (language == RiotLanguage.INSTANCE) {
            val parent = tag.parent

            if (parent is PsiFile || parent is HtmlDocumentImpl) {
                //only top tags
                return RiotComponentDeclarationXmlDescriptor(tag)
            }
            val defaultDescriptor: XmlNSDescriptor? = tag.getNSDescriptor(tag.namespace, false)

            return RiotElementXmlDescriptor(defaultDescriptor, tag, name)
        }
        if (language == HTMLLanguage.INSTANCE) {

            val componentTag = RiotComponentIndex.findComponent(name, tag) ?: return null
            return RiotComponentXmlDescriptor(componentTag, name)
        }

        return null
    }
}