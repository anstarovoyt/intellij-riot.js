package intellij.riot.lang

import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.application.EDT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import intellij.riot.lang.v3.Riot3HtmlFileType

class RiotFileTypeReassignmentActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        val property = "riot_reassign_file_type"
        if (PropertiesComponent.getInstance().getBoolean(property, false)) return

        val riotChanged = reAssign("riot", RiotHtmlFileType.INSTANCE)
        val tagChanged = reAssign("tag", Riot3HtmlFileType.INSTANCE)
        if (riotChanged || tagChanged) {
            withContext(Dispatchers.EDT) {
                val groupDisplayId = "Riot notification"
                val notificationTitle = "Riot file type updating"
                val notificationMessage = "File Type associations are updated from 'HTML' to 'Riot Framework'"
                val notificationType = NotificationType.INFORMATION
                val notification = com.intellij.notification.Notification(groupDisplayId, notificationTitle, notificationMessage, notificationType)
                Notifications.Bus.notify(notification)
            }
        }

        PropertiesComponent.getInstance().setValue(property, true)
    }

    private fun reAssign(extension: String, fileType: FileType): Boolean {
        val tag = FileTypeManager.getInstance().getFileTypeByExtension(extension)
        if (tag == HtmlFileType.INSTANCE) {
            WriteAction.run<RuntimeException> {
                FileTypeManager.getInstance().removeAssociatedExtension(HtmlFileType.INSTANCE, extension)
                FileTypeManager.getInstance().associateExtension(fileType, extension)
            }
            
            return true
        }
        return false
    }
}
