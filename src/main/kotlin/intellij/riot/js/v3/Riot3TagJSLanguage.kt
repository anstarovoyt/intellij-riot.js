package intellij.riot.js.v3

import com.intellij.lang.PsiBuilder
import com.intellij.lang.javascript.DialectOptionHolder
import com.intellij.lang.javascript.JSLanguageDialect
import com.intellij.lang.javascript.JavaScriptSupportLoader
import com.intellij.lang.javascript.parsing.JavaScriptParser

/**
 * Describes JavaScript inside "script" tag for Riot.js 3.
 * We have to keep a separate language for it because it introduce non-trivial syntax:
 * do1() {hello} <- at top level of the script tag
 */
class Riot3TagJSLanguage : JSLanguageDialect("Riot3TagJS", DialectOptionHolder.ECMA_6, JavaScriptSupportLoader.ECMA_SCRIPT_6) {
    companion object {
        val INSTANCE = Riot3TagJSLanguage()
    }

    override fun getFileExtension(): String = "js"
    override fun createParser(builder: PsiBuilder): JavaScriptParser<*, *, *, *> {
        return Riot3TagJSParser(builder)
    }
}