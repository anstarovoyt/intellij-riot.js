package intellij.riot.js

import com.intellij.codeInspection.InspectionProfileEntry
import com.intellij.lang.javascript.highlighting.IntentionAndInspectionFilter
import com.sixrr.inspectjs.validity.BadExpressionStatementJSInspection
import com.sixrr.inspectjs.validity.ThisExpressionReferencesGlobalObjectJSInspection

class RiotJSInspectionFilter : IntentionAndInspectionFilter() {
    override fun isSupportedInspection(inspectionToolId: String?): Boolean =
            inspectionToolId != InspectionProfileEntry.getShortName(BadExpressionStatementJSInspection::class.java.simpleName) &&
            inspectionToolId != InspectionProfileEntry.getShortName(ThisExpressionReferencesGlobalObjectJSInspection::class.java.simpleName)
}