package intellij.riot.js

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.lang.javascript.JSInjectionBracesUtil
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.xml.*
import com.intellij.util.containers.ContainerUtil
import intellij.riot.lang.IRiotFileType

class RiotJSInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        if (context.containingFile?.fileType !is IRiotFileType) return

        if (context is XmlText) {
            JSInjectionBracesUtil
                    .injectInXmlTextByDelimiters(registrar, context, RiotJSLanguage.INSTANCE, "{", "}")
        }
        if (context is XmlAttributeValue) {
            val prevSibling = context.prevSibling
            if (prevSibling == XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER && prevSibling.text == "{") {
                registrar.startInjecting(RiotJSLanguage.INSTANCE, null).addPlace(null as String?, null as String?, 
                        context as PsiLanguageInjectionHost, 
                        TextRange(0, (context as PsiElement).textLength)).doneInjecting()
            } else {
                JSInjectionBracesUtil
                        .injectInXmlTextByDelimiters(registrar, context, RiotJSLanguage.INSTANCE, "{", "}")
            }
        }
    }

    override fun elementsToInjectIn(): List<Class<out PsiElement>> = ContainerUtil.immutableList(XmlText::class.java, XmlAttributeValue::class.java)
}
