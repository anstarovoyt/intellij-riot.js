package intellij.riot.js.v3

import com.intellij.lang.PsiBuilder
import com.intellij.lang.javascript.DialectOptionHolder
import com.intellij.lang.javascript.JSLanguageDialect
import com.intellij.lang.javascript.JavaScriptSupportLoader
import com.intellij.lang.javascript.parsing.JavaScriptParser

class Riot3TagJSLanguage : JSLanguageDialect("Riot3TagJS", DialectOptionHolder.ECMA_6, JavaScriptSupportLoader.ECMA_SCRIPT_6) {
    companion object {
        val INSTANCE = Riot3TagJSLanguage()
    }

    override fun getFileExtension(): String = "js"
    override fun createParser(builder: PsiBuilder): JavaScriptParser<*, *, *, *> {
        return Riot3TagJSParser(builder)
    }
}