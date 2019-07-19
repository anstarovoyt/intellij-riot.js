package riot.intellij.js

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.lang.javascript.JSInjectionBracesUtil
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlText
import com.intellij.util.containers.ContainerUtil

class RiotJSInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        JSInjectionBracesUtil
                .injectInXmlTextByDelimiters(registrar, context, RiotJSLanguage.INSTANCE, "{", "}")
    }

    override fun elementsToInjectIn(): List<Class<out PsiElement>> = ContainerUtil.immutableList(XmlText::class.java)
}
