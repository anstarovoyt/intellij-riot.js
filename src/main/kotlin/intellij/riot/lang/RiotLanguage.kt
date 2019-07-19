package intellij.riot.lang

import com.intellij.lang.Language
import com.intellij.lang.html.HTMLLanguage
import com.intellij.lang.javascript.types.JSEmbeddedContentElementType
import com.intellij.lang.xml.XMLLanguage
import com.intellij.lexer.Lexer
import intellij.riot.js.RiotJSLanguage

class RiotLanguage : XMLLanguage(HTMLLanguage.INSTANCE, "Riot") {
    
    companion object {
        val INSTANCE: RiotLanguage = RiotLanguage()
    }
}