package intellij.riot

import com.intellij.lang.javascript.inspections.JSUnresolvedVariableInspection
import com.intellij.lang.javascript.modules.TypeScriptCheckImportInspection
import com.intellij.lang.typescript.inspections.TypeScriptUnresolvedFunctionInspection
import com.intellij.lang.typescript.inspections.TypeScriptUnresolvedVariableInspection
import com.intellij.lang.typescript.inspections.TypeScriptValidateTypesInspection
import com.intellij.testFramework.UsefulTestCase
import intellij.riot.lang.RiotHtmlFileType
import junit.framework.TestCase
import org.junit.Test

class RiotHtmlTest : RiotTestBase() {

    @Test
    fun testCssInTags() {
        myFixture.configureByText("my.css", ".myClass {}")
        myFixture.configureByText("test.riot", "<Test><div class='myCla<caret>ss'></div></Test>")
        val ref = myFixture.getReferenceAtCaretPosition()
        TestCase.assertNotNull(ref?.resolve())
    }

    @Test
    fun testTagSimpleAttributes() {
        myFixture.configureByText("test.riot", "<Test><div <caret>></div></Test>")
        val results = myFixture.completeBasic()
        val strings = results.map { it.lookupString }.toSet()
        UsefulTestCase.assertContainsElements(strings, "class")
    }

    @Test
    fun testTagSimpleCompletion() {
        myFixture.configureByText("test.riot", "<Test><<caret></Test>")
        val results = myFixture.completeBasic()
        val strings = results.map { it.lookupString }.toSet()
        UsefulTestCase.assertContainsElements(strings, "div", "script", "style", "h1")
    }

    @Test
    fun testTypeScript() {
        myFixture.enableInspections(TypeScriptCheckImportInspection(),
                TypeScriptValidateTypesInspection(),
                TypeScriptUnresolvedVariableInspection(),
                TypeScriptUnresolvedFunctionInspection(),
                JSUnresolvedVariableInspection())

        myFixture.configureByText(
                RiotHtmlFileType.INSTANCE,
                "<test>\n" +
                        "<script type=\"ts\">\n" +
                        "var a:number  = <error>\"\"</error>;\n" +
                        "export default {}\n" +
                        "</script>\n" +
                        "</test>"
        )

        myFixture.testHighlighting()
    }

    @Test
    fun testScss() {
        myFixture.configureByText(
                RiotHtmlFileType.INSTANCE,
                """<test>
<style type="scss">
    :host {
      margin: 0;
      padding: 0;

      & * {
        box-sizing: border-box;
        }
    }
  </style> 
</test>"""
        )

        myFixture.testHighlighting()
    }

    @Test
    fun testSass() {
        myFixture.configureByText(
                RiotHtmlFileType.INSTANCE,
                """<test>
<style type="sass">
body
    margin: 0
    padding: 0
  </style> 
</test>"""
        )

        myFixture.testHighlighting()
    }
}