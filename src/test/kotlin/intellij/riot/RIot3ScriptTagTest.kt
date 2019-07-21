package intellij.riot

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import intellij.riot.js.RiotElements
import intellij.riot.lang.v3.Riot3HtmlFileType

class RIot3ScriptTagTest : BasePlatformTestCase() {
    override fun setUp() {
        RiotElements.EMBEDDED_RIOT3_TAG
        super.setUp()
    }

    fun testLiteralMemberTopLevel() {
        myFixture.configureByText(
                Riot3HtmlFileType.INSTANCE,
                "<test>\n" +
                        "<script>\n" +
                        "  do1() {hello()}\n" +
                        "</script>\n" +
                        "</test>"
        )

        myFixture.testHighlighting()
    }

    fun testScriptTagMethodCall() {
        myFixture.configureByText(
                Riot3HtmlFileType.INSTANCE,
                "<test>\n" +
                        "<script>\n" +
                        "function hello() {}\n" +
                        "hello()" +
                        "</script>\n" +
                        "</test>"
        )

        myFixture.testHighlighting()
    }

}