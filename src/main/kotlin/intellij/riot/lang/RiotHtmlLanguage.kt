package intellij.riot.lang

import com.intellij.lang.html.HTMLLanguage
import com.intellij.lang.xml.XMLLanguage

class RiotHtmlLanguage : XMLLanguage(HTMLLanguage.INSTANCE, "RiotHtml"), IRiotHtmlLanguage {
    
    companion object {
        val INSTANCE: RiotHtmlLanguage = RiotHtmlLanguage()
    }
}