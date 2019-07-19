package riot.intellij.lang

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class RiotFileType : LanguageFileType(RiotLanguage.INSTANCE) {
    override fun getIcon(): Icon? = AllIcons.FileTypes.Html
    override fun getName(): String = "Riot"
    override fun getDefaultExtension(): String = "riot"
    override fun getDescription(): String = "Riot framework 4+ files"
    
    companion object {
        val INSTANCE = RiotFileType()
    }
}