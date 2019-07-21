package intellij.riot

import com.intellij.codeInspection.htmlInspections.HtmlUnknownAttributeInspection
import com.intellij.codeInspection.htmlInspections.HtmlUnknownTagInspection
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import intellij.riot.lang.v3.Riot3HtmlFileType

class Riot3ScriptTagTest : BasePlatformTestCase() {
    override fun setUp() {
        super.setUp()
        myFixture.enableInspections(HtmlUnknownTagInspection(), HtmlUnknownAttributeInspection())
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