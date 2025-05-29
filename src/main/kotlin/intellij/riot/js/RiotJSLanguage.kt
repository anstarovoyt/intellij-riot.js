package intellij.riot.js

import com.intellij.lang.javascript.DialectOptionHolder
import com.intellij.lang.javascript.JSLanguageDialect
import com.intellij.lang.javascript.JavaScriptSupportLoader

/**
 * Language for js inside Riot.js template interpolations: <a class={ RiotJSLanguage } />
 */
class RiotJSLanguage : JSLanguageDialect("RiotJS", DialectOptionHolder.JS_WITHOUT_JSX, JavaScriptSupportLoader.ECMA_SCRIPT_6) {

    companion object {
        @JvmField
        val INSTANCE = RiotJSLanguage()
    }
}
