package intellij.riot.run

import com.intellij.javascript.debugger.JavaScriptDebugAwareBase
import intellij.riot.lang.v3.Riot3HtmlFileType

class Riot3DebugAware : JavaScriptDebugAwareBase(Riot3HtmlFileType.INSTANCE)