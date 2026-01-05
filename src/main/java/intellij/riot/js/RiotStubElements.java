package intellij.riot.js;

import com.intellij.lang.Language;
import com.intellij.lang.javascript.types.JSEmbeddedContentElementType;
import com.intellij.lexer.Lexer;
import intellij.riot.js.v3.Riot3TagJSLanguage;

public interface RiotStubElements {
    String EMBEDDED_RIOT_3_TAG = "EMBEDDED_RIOT3_TAG";


    JSEmbeddedContentElementType EMBEDDED_RIOT3_TAG = new JSEmbeddedContentElementType(
            Riot3TagJSLanguage.Companion.getINSTANCE(), EMBEDDED_RIOT_3_TAG
    ) {
        @Override
        protected Lexer createStripperLexer(Language baseLanguage) {
            return null;
        }
    };
}
