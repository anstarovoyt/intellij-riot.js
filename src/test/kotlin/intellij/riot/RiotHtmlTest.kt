package intellij.riot

import com.intellij.testFramework.UsefulTestCase
import junit.framework.TestCase

class RiotHtmlTest : RiotTestBase() {
    fun testCssInTags() {
        myFixture.configureByText("my.css", ".myClass {}")
        myFixture.configureByText("test.riot", "<Test><div class='myCla<caret>ss'></div></Test>")
        val ref = myFixture.getReferenceAtCaretPosition()
        TestCase.assertNotNull(ref?.resolve())
    }
    
    fun testTagSimpleAttributes() {
        myFixture.configureByText("test.riot", "<Test><div <caret>></div></Test>")
        val results = myFixture.completeBasic()
        val strings = results.map { it.lookupString }.toSet()
        UsefulTestCase.assertContainsElements(strings, "class")
    }   
    
    fun testTagSimpleCompletion() {
        myFixture.configureByText("test.riot", "<Test><<caret></Test>")
        val results = myFixture.completeBasic()
        val strings = results.map { it.lookupString }.toSet()
        UsefulTestCase.assertContainsElements(strings, "div", "script", "style", "h1")
    }
}