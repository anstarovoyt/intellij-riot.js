package intellij.riot.lang.v3

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import intellij.riot.lang.IRiotFileType
import intellij.riot.lang.RiotFileType
import intellij.riot.lang.RiotLanguage
import javax.swing.Icon

class Riot3FileType : LanguageFileType(RiotLanguage.INSTANCE), IRiotFileType {
    override fun getIcon(): Icon? = AllIcons.FileTypes.Html
    override fun getName(): String = "Riot3"
    override fun getDefaultExtension(): String = "tag"
    override fun getDescription(): String = "Riot framework 3- files"

    companion object {
        val INSTANCE = RiotFileType()
    }
}