package intellij.riot

import com.intellij.testFramework.UsefulTestCase
import intellij.riot.lang.v3.Riot3HtmlFileType
import org.junit.Test

class Riot3ScriptTagTest : RiotTestBase() {

    @Test
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

    @Test
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

    @Test
    fun testScriptTagAsyncFunction() {
        myFixture.configureByText(
                Riot3HtmlFileType.INSTANCE,
                "<test>\n" +
                        "<script>\n" +
                        "  async do1() { await hello() }\n" +
                        "</script>\n" +
                        "</test>"
        )

        myFixture.testHighlighting()
    }

    @Test
    fun testTagSimpleCompletion() {
        myFixture.configureByText("test.tag", "<Test><<caret></Test>")
        val results = myFixture.completeBasic()
        val strings = results.map { it.lookupString }.toSet()
        UsefulTestCase.assertContainsElements(strings, "div", "script")
    }
}