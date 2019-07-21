package intellij.riot.xml

import com.intellij.lang.html.HTMLLanguage
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.html.HtmlDocumentImpl
import com.intellij.psi.impl.source.xml.XmlElementDescriptorProvider
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.XmlElementDescriptor
import com.intellij.xml.XmlNSDescriptor
import intellij.riot.lang.IRiotHtmlLanguage

class RiotComponentDescriptorProvider : XmlElementDescriptorProvider {
    override fun getDescriptor(tag: XmlTag?): XmlElementDescriptor? {
        if (tag == null) return null
        val name = tag.name
        val language = tag.containingFile?.language
        if (language !is IRiotHtmlLanguage) {
            return if (language == HTMLLanguage.INSTANCE) findRiotComponent(name, tag) else null
        }

        val parent = tag.parent

        if (parent is PsiFile || parent is HtmlDocumentImpl) {
            //only top tags
            return RiotComponentDeclarationXmlDescriptor(tag)
        }
        val candidate = findRiotComponent(name, tag)
        if (candidate != null) return candidate

        val defaultDescriptor: XmlNSDescriptor? = tag.getNSDescriptor(tag.namespace, false)

        return RiotElementXmlDescriptor(defaultDescriptor, tag, name)
    }

    private fun findRiotComponent(name: String, tag: XmlTag): RiotComponentXmlDescriptor? {
        val componentTag = RiotComponentIndex.findComponent(name, tag) ?: return null
        return RiotComponentXmlDescriptor(componentTag, name)
    }
}