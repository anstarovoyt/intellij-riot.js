package riot.intellij.lang

import com.intellij.psi.tree.IElementType

class RiotHtmlTokenType(name: String): IElementType(name, RiotLanguage.INSTANCE) {
}

var INTERPOLATION = RiotHtmlTokenType("RIOT:INTERPOLATION")