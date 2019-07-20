package intellij.riot.js

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.lang.javascript.JSInjectionBracesUtil
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlText
import com.intellij.util.containers.ContainerUtil
import intellij.riot.lang.IRiotFileType

class RiotJSInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        if (context.containingFile?.fileType !is IRiotFileType) return

        if (context is XmlText) {
            JSInjectionBracesUtil
                    .injectInXmlTextByDelimiters(registrar, context, RiotJSLanguage.INSTANCE, "{", "}")
        }
        if (context is XmlAttribute) {
            val valueElement = context.valueElement ?: return
            JSInjectionBracesUtil
                    .injectInXmlTextByDelimiters(registrar, valueElement, RiotJSLanguage.INSTANCE, "{", "}")
        }
    }

    override fun elementsToInjectIn(): List<Class<out PsiElement>> = ContainerUtil.immutableList(XmlText::class.java, XmlAttribute::class.java)
}
