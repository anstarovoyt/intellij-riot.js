package intellij.riot.xml

import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.XmlAttributeDescriptor
import com.intellij.xml.XmlElementDescriptor
import com.intellij.xml.XmlElementsGroup
import com.intellij.xml.XmlNSDescriptor
import com.intellij.xml.impl.schema.AnyXmlAttributeDescriptor

val riotSpecialTags = setOf("slot", "then", "else", "elseif", "if", "yield")

class RiotElementXmlDescriptor(private val parentNSDescriptor: XmlNSDescriptor?, private val tag: XmlTag, private val tagName: String) : XmlElementDescriptor {

    override fun getDefaultValue(): String? = null
    override fun getName(p0: PsiElement?): String = name
    override fun getName(): String = tagName
    override fun getElementsDescriptors(p0: XmlTag?): Array<XmlElementDescriptor>? = getXmlElementDescriptorFormParent(tag)?.getElementsDescriptors(p0)
    override fun init(p0: PsiElement?) {}
    override fun getContentType(): Int = XmlElementDescriptor.CONTENT_TYPE_UNKNOWN
    override fun getTopGroup(): XmlElementsGroup? = null
    override fun getDefaultName(): String = name
    override fun getNSDescriptor(): XmlNSDescriptor? = parentNSDescriptor
    override fun getQualifiedName(): String = name
    override fun getElementDescriptor(p0: XmlTag?, p1: XmlTag?): XmlElementDescriptor? = getXmlElementDescriptorFormParent(p1)?.getElementDescriptor(p0, p1)
    override fun getDeclaration(): PsiElement? {
        return if (riotSpecialTags.contains(name)) tag else getXmlElementDescriptorFormParent(tag)?.declaration
    }

    override fun getAttributeDescriptor(p0: XmlAttribute?): XmlAttributeDescriptor? = getAttributeDescriptor(p0?.name, null)
    override fun getAttributesDescriptors(p0: XmlTag?): Array<XmlAttributeDescriptor>? = getXmlElementDescriptorFormParent(p0)?.getAttributesDescriptors(p0)

    override fun getAttributeDescriptor(name: String?, context: XmlTag?): XmlAttributeDescriptor? {
        val parent = getXmlElementDescriptorFormParent(context)

        val attributeDescriptor = parent?.getAttributeDescriptor(name, context)
        if (attributeDescriptor != null) return attributeDescriptor

        return AnyXmlAttributeDescriptor(name)
    }

    private fun getXmlElementDescriptorFormParent(context: XmlTag?): XmlElementDescriptor? {
        return parentNSDescriptor?.getElementDescriptor(context ?: tag)
    }
}
