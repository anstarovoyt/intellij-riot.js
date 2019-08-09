package intellij.riot.css

import com.intellij.lang.Language
import com.intellij.psi.css.EmbeddedCssProvider
import intellij.riot.lang.IRiotHtmlLanguage

class RiotEmbeddedCssProvider: EmbeddedCssProvider() {
    override fun enableEmbeddedCssFor(language: Language): Boolean {
        return language is IRiotHtmlLanguage
    }
}