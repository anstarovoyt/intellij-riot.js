package riot.intellij.lang

import com.intellij.psi.tree.IElementType

class RiotHtmlTokenType(name: String): IElementType(name, RiotLanguage.INSTANCE) {
}

var INTERPOLATION_START = RiotHtmlTokenType("RIOT:INTERPOLATION_START")
var INTERPOLATION_END = RiotHtmlTokenType("RIOT:INTERPOLATION_END")