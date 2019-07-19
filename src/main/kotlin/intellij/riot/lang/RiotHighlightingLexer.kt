package intellij.riot.lang

import com.intellij.lexer.HtmlHighlightingLexer
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.tree.IElementType
import com.intellij.psi.xml.XmlTokenType
import intellij.riot.js.RiotJSLanguage

class RiotHighlightingLexer : HtmlHighlightingLexer() {

    private var myTokenType: IElementType? = null
    private var myTokenStart: Int = 0
    private var myTokenEnd: Int = 0
    
    override fun getTokenType(): IElementType? {
        if (myTokenType != null) return myTokenType

        val tokenType = delegate.tokenType

        if (tokenType == XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN && StringUtil.startsWith(tokenSequence, "{")) {
            myTokenStart = delegate.tokenStart;
            myTokenEnd = skipToTheEndInterpolation()
            myTokenType = RiotJSLanguage.EMBEDDED_JS
            return myTokenType
        }
        return super.getTokenType()
    }

    override fun advance() {
        myTokenType = null
        super.advance()
        tokenType
    }

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        myTokenType = null
        super.start(buffer, startOffset, endOffset, initialState)
        tokenType
    }

    private fun skipToTheEndInterpolation(): Int {
        val base = delegate
        var tokenEnd = base.tokenEnd
        var lastState = 0
        var lastStart = 0

        val buf = base.bufferSequence
        while (true) {
            val currentSequence = tokenSequence
            if (StringUtil.equal(currentSequence, ">", true)) {
                break
            }
            
            tokenEnd = base.tokenEnd;
            lastState = base.state;
            lastStart = base.tokenStart;

            if (tokenEnd == bufferEnd) break;
            base.advance();

            if (StringUtil.equal(currentSequence, "}", true)) break
        }

        base.start(buf, lastStart, bufferEnd, lastState);
        base.tokenType;

        return tokenEnd
    }

    override fun getTokenStart(): Int {
        return if (myTokenType != null) {
            myTokenStart
        } else super.getTokenStart()
    }

    override fun getTokenEnd(): Int {
        return if (myTokenType != null) {
            myTokenEnd
        } else super.getTokenEnd()
    }
}