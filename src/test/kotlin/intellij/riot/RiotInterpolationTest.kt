package intellij.riot

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import intellij.riot.lang.RiotFileType

class RiotInterpolationTest : BasePlatformTestCase() {
    fun testSimple() {
        myFixture.configureByText(
                RiotFileType.INSTANCE,
                "<test><a href={hello} /></test>"
        )

        myFixture.testHighlighting()
    }

    fun testSimpleArrow() {
        myFixture.configureByText(
                RiotFileType.INSTANCE,
                "<test><a href={() => 1} /></test>"
        )

        myFixture.testHighlighting()
    }
}
