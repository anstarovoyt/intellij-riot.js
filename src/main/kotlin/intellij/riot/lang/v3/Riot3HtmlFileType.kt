package intellij.riot.lang.v3

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import intellij.riot.lang.IRiotFileType
import javax.swing.Icon

class Riot3HtmlFileType : LanguageFileType(Riot3HtmlLanguage.INSTANCE), IRiotFileType {
    override fun getIcon(): Icon = AllIcons.FileTypes.Html
    override fun getName(): String = "Riot3Html"
    override fun getDefaultExtension(): String = "tag"
    override fun getDescription(): String = "Riot framework 3- files"

    companion object {
        @JvmField
        val INSTANCE = Riot3HtmlFileType()
    }
}
