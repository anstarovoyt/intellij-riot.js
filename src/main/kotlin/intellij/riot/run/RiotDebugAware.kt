package intellij.riot.run

import com.intellij.javascript.debugger.JavaScriptDebugAwareBase
import intellij.riot.lang.RiotHtmlFileType

class RiotDebugAware : JavaScriptDebugAwareBase(RiotHtmlFileType.INSTANCE)