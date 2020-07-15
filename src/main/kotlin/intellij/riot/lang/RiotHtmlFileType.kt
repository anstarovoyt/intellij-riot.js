package intellij.riot.lang

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class RiotHtmlFileType : LanguageFileType(RiotHtmlLanguage.INSTANCE), IRiotFileType {
    override fun getIcon(): Icon? = AllIcons.FileTypes.Html
    override fun getName(): String = "RiotHtml"
    override fun getDefaultExtension(): String = "riot"
    override fun getDescription(): String = "Riot framework 4+ files"
    
    companion object {
        @JvmField
        val INSTANCE = RiotHtmlFileType()        
    }
}

