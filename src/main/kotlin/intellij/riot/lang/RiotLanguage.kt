package intellij.riot.lang

import com.intellij.lang.html.HTMLLanguage
import com.intellij.lang.xml.XMLLanguage

class RiotLanguage : XMLLanguage(HTMLLanguage.INSTANCE, "Riot") {
    companion object {
        val INSTANCE: RiotLanguage = RiotLanguage()
    }
}