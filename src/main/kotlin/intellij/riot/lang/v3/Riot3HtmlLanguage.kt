package intellij.riot.lang.v3

import com.intellij.lang.html.HTMLLanguage
import com.intellij.lang.xml.XMLLanguage
import intellij.riot.lang.IRiotHtmlLanguage

class Riot3HtmlLanguage : XMLLanguage(HTMLLanguage.INSTANCE, "Riot3Html"), IRiotHtmlLanguage {
    
    companion object {
        val INSTANCE: Riot3HtmlLanguage = Riot3HtmlLanguage()
    }
}