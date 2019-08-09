package intellij.riot

import junit.framework.TestCase

class RiotHtmlTest : RiotTestBase() {
    fun testCssInTags() {
        myFixture.configureByText("my.css", ".myClass {}")
        myFixture.configureByText("test.riot", "<div class='myCla<caret>ss'></div>")
        val ref = myFixture.getReferenceAtCaretPosition()
        TestCase.assertNotNull(ref?.resolve())
    }
}