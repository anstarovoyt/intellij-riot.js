package intellij.riot

import com.intellij.lang.javascript.inspections.JSUnresolvedVariableInspection
import com.intellij.lang.javascript.modules.TypeScriptCheckImportInspection
import com.intellij.lang.typescript.inspections.TypeScriptUnresolvedFunctionInspection
import com.intellij.lang.typescript.inspections.TypeScriptUnresolvedVariableInspection
import com.intellij.lang.typescript.inspections.TypeScriptValidateTypesInspection
import intellij.riot.lang.RiotHtmlFileType

class RiotScriptTagTest : RiotTestBase() {
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
}