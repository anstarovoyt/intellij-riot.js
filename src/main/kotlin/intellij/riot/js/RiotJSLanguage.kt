package intellij.riot.js

import com.intellij.lang.Language
import com.intellij.lang.PsiBuilder
import com.intellij.lang.javascript.DialectOptionHolder
import com.intellij.lang.javascript.JSLanguageDialect
import com.intellij.lang.javascript.JavaScriptSupportLoader
import com.intellij.lang.javascript.parsing.JavaScriptParser
import com.intellij.lang.javascript.types.JSEmbeddedContentElementType
import com.intellij.lexer.Lexer

class RiotJSLanguage : JSLanguageDialect("RiotJS", DialectOptionHolder.ECMA_6, JavaScriptSupportLoader.ECMA_SCRIPT_6) {

    companion object {
        val INSTANCE = RiotJSLanguage()
        val EMBEDDED_JS: JSEmbeddedContentElementType by lazy {
            object : JSEmbeddedContentElementType(INSTANCE, "RiotJS") {
                override fun createStripperLexer(baseLanguage: Language): Lexer? = null
            }
        }
    }

    override fun getFileExtension(): String = "js"
    override fun createParser(builder: PsiBuilder): JavaScriptParser<*, *, *, *> {
        return RiotJavaScriptLanguageParser(builder)
    }

}