package intellij.riot

import intellij.riot.lang.RiotHtmlFileType
import org.junit.Test

class RiotInterpolationTest : RiotTestBase() {

    @Test
    fun testSimple() {
        myFixture.configureByText(
                RiotHtmlFileType.INSTANCE,
                "<test><a href={hello} /></test>"
        )

        myFixture.testHighlighting()
    }

    @Test
    fun testSimpleArrow() {
        myFixture.configureByText(
                RiotHtmlFileType.INSTANCE,
                "<test><a href={() => 1} /></test>"
        )

        myFixture.testHighlighting()
    }
}
