package intellij.riot

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import intellij.riot.lang.RiotHtmlFileType

class RiotInterpolationTest : BasePlatformTestCase() {
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
