package intellij.riot.js

import com.intellij.lang.Language
import com.intellij.lang.javascript.types.JSEmbeddedContentElementType
import com.intellij.lexer.Lexer

class RiotElements {
    companion object {
        val EMBEDDED_JS: JSEmbeddedContentElementType by lazy {
            object : JSEmbeddedContentElementType(RiotJSLanguage.INSTANCE, "RiotJS") {
                override fun createStripperLexer(baseLanguage: Language): Lexer? = null
            }
        }
    }
}