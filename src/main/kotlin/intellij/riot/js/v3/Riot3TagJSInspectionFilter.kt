package intellij.riot.js.v3

import com.intellij.codeInspection.InspectionProfileEntry
import com.intellij.lang.javascript.highlighting.IntentionAndInspectionFilter
import com.sixrr.inspectjs.validity.BadExpressionStatementJSInspection
import com.sixrr.inspectjs.validity.ThisExpressionReferencesGlobalObjectJSInspection

/**
 * Riot 3 syntax uses top-level "this" in script tags so we have to suppress the inspections
 */
class Riot3TagJSInspectionFilter : IntentionAndInspectionFilter() {
    override fun isSupportedInspection(inspectionToolId: String?): Boolean =
            inspectionToolId != InspectionProfileEntry.getShortName(BadExpressionStatementJSInspection::class.java.simpleName) &&
            inspectionToolId != InspectionProfileEntry.getShortName(ThisExpressionReferencesGlobalObjectJSInspection::class.java.simpleName)
}