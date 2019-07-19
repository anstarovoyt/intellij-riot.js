package intellij.riot.js

import com.intellij.lang.PsiBuilder
import com.intellij.lang.javascript.DialectOptionHolder
import com.intellij.lang.javascript.JSLanguageDialect
import com.intellij.lang.javascript.JavaScriptSupportLoader
import com.intellij.lang.javascript.parsing.JavaScriptParser

class RiotJSLanguage : JSLanguageDialect("RiotJS", DialectOptionHolder.ECMA_6, JavaScriptSupportLoader.ECMA_SCRIPT_6) {

    companion object {
        val INSTANCE = RiotJSLanguage()
    }
    
    override fun getFileExtension(): String = "js"
    override fun createParser(builder: PsiBuilder): JavaScriptParser<*, *, *, *> {
        return RiotJavaScriptLanguageParser(builder)
    }

}