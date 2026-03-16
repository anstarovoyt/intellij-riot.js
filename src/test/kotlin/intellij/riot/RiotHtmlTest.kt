package intellij.riot

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.lang.javascript.inspections.JSUnresolvedReferenceInspection
import com.intellij.lang.javascript.inspections.TypeScriptCheckImportInspection
import com.intellij.lang.typescript.inspections.TypeScriptUnresolvedReferenceInspection
import com.intellij.lang.typescript.inspections.TypeScriptValidateTypesInspection
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.fileTypes.FileTypeManager
import intellij.riot.lang.RiotHtmlFileType
import intellij.riot.lang.RiotHtmlLanguage
import intellij.riot.lang.RiotHtmlParserDefinition
import org.junit.Test
import kotlin.jvm.java

@Suppress("JUnitMixedFramework")
class RiotHtmlTest : RiotTestBase() {

    @Test
    fun testPluginLoaded() {
        val pluginId = PluginId.getId("riot.js")
        val plugin = PluginManagerCore.getPlugin(pluginId)
        assertNotNull(plugin)
//        val reason = PluginManagerCore.getPluginNonLoadReason(pluginId)
//        assertNull(reason?.detailedMessage)
        assertTrue(PluginManagerCore.isLoaded(pluginId))
    }

    @Test
    fun testJSPluginLoaded() {
        val pluginId = PluginId.getId("JavaScript")
        val plugin = PluginManagerCore.getPlugin(pluginId)
        assertNotNull(plugin)
//        val reason = PluginManagerCore.getPluginNonLoadReason(pluginId)
//        assertNull(reason?.detailedMessage)
        assertTrue(PluginManagerCore.isLoaded(pluginId))
    }

    @Test
    fun testContext() {
        assertEquals(
            RiotHtmlFileType.INSTANCE,
            FileTypeManager.getInstance().getFileTypeByFileName("test.riot")
        )
        val lang = com.intellij.lang.Language.findLanguageByID(RiotHtmlLanguage.INSTANCE.id)
        assertEquals(RiotHtmlLanguage.INSTANCE, lang)
        assertEquals(
            RiotHtmlParserDefinition::class.java,
            com.intellij.lang.LanguageParserDefinitions.INSTANCE.forLanguage(lang!!).javaClass
        )
    }

    @Test
    fun testCssInTags() {
        myFixture.configureByText("my.css", ".myClass {}")
        myFixture.configureByText("test.riot", "<Test><div class='myCla<caret>ss'></div></Test>")
        val ref = myFixture.getReferenceAtCaretPosition()
        assertNotNull(ref?.resolve())
    }

    @Test
    fun testTagSimpleAttributes() {
        myFixture.configureByText("test.riot", "<Test><div <caret>></div></Test>")
        val results = myFixture.completeBasic()
        val strings = results.map { it.lookupString }.toSet()
        assertContainsElements(strings, "class")
    }

    @Test
    fun testTagSimpleCompletion() {
        myFixture.configureByText("test.riot", "<Test><<caret></Test>")
        val results = myFixture.completeBasic()
        val strings = results.map { it.lookupString }.toSet()
        assertContainsElements(strings, "div", "script", "style", "h1")
    }

    @Test
    fun testTypeScript() {
        myFixture.enableInspections(
            TypeScriptCheckImportInspection(),
            TypeScriptValidateTypesInspection(),
            JSUnresolvedReferenceInspection(),
            TypeScriptUnresolvedReferenceInspection()
        )

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
