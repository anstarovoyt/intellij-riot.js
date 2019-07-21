package intellij.riot

import intellij.riot.lang.RiotHtmlFileType

class RiotInterpolationTest : RiotTestBase() {
    fun testSimple() {
        myFixture.configureByText(
                RiotHtmlFileType.INSTANCE,
                "<test><a href={hello} /></test>"
        )

        myFixture.testHighlighting()
    }

    fun testSimpleArrow() {
        myFixture.configureByText(
                RiotHtmlFileType.INSTANCE,
                "<test><a href={() => 1} /></test>"
        )

        myFixture.testHighlighting()
    }
}
