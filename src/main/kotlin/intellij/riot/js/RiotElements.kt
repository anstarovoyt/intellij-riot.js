package intellij.riot.js

import com.intellij.lang.Language
import com.intellij.lang.javascript.types.JSEmbeddedContentElementType
import com.intellij.lexer.Lexer
import intellij.riot.js.v3.Riot3TagJSLanguage

/**
 * A separate class is required for in-time initialization stub elements
 */
class RiotElements {
    companion object {
        val EMBEDDED_RIOT3_TAG: JSEmbeddedContentElementType =
                object : JSEmbeddedContentElementType(Riot3TagJSLanguage.INSTANCE, "Riot3TagJS") {
                    override fun createStripperLexer(baseLanguage: Language): Lexer? = null
                }
    }
}