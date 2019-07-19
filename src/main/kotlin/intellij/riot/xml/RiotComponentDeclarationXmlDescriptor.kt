package intellij.riot.xml

import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlTag
import com.intellij.xml.XmlAttributeDescriptor
import com.intellij.xml.XmlElementDescriptor
import com.intellij.xml.XmlElementDescriptor.CONTENT_TYPE_UNKNOWN
import com.intellij.xml.XmlElementsGroup
import com.intellij.xml.XmlNSDescriptor

class RiotComponentDeclarationXmlDescriptor(private val tag: XmlTag) : XmlElementDescriptor {

    private val myName = tag.name
    override fun getDefaultValue(): String? = null
    override fun getName(p0: PsiElement?): String = name
    override fun getName(): String = myName
    override fun getElementsDescriptors(p0: XmlTag?): Array<XmlElementDescriptor> = emptyArray()
    override fun init(p0: PsiElement?) {}
    override fun getContentType(): Int = CONTENT_TYPE_UNKNOWN
    override fun getTopGroup(): XmlElementsGroup? = null
    override fun getDefaultName(): String = name
    override fun getNSDescriptor(): XmlNSDescriptor? = null
    override fun getQualifiedName(): String = name
    override fun getElementDescriptor(p0: XmlTag?, p1: XmlTag?): XmlElementDescriptor? = null
    override fun getDeclaration(): PsiElement? = tag
    override fun getAttributeDescriptor(p0: String?, p1: XmlTag?): XmlAttributeDescriptor? = null
    override fun getAttributeDescriptor(p0: XmlAttribute?): XmlAttributeDescriptor? = null
    override fun getAttributesDescriptors(p0: XmlTag?): Array<XmlAttributeDescriptor> = emptyArray()

}