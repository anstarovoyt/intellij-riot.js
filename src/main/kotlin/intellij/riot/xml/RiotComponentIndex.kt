package intellij.riot.xml

import com.intellij.openapi.project.DumbService
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.intellij.util.Processor
import com.intellij.util.indexing.*
import com.intellij.util.io.EnumeratorStringDescriptor
import com.intellij.util.io.KeyDescriptor
import intellij.riot.lang.IRiotFileType

/**
 * name-based index of all Riot.js templates
 */
class RiotComponentIndex : ScalarIndexExtension<String>() {
    companion object {
        private val id = ID.create<String, Void>("RiotComponentIndex")

        fun findComponent(name: String, context: PsiElement): PsiElement? {
            val mutableListOf = mutableListOf<VirtualFile>()
            val project = context.project
            if (DumbService.isDumb(project)) return null

            FileBasedIndex.getInstance().getFilesWithKey(id, setOf(name), Processor {
                mutableListOf.add(it)
                return@Processor false
            }, GlobalSearchScope.allScope(project))

            val file = mutableListOf.firstOrNull() ?: return null

            return context.manager.findFile(file)
        }
    }


    override fun getName(): ID<String, Void> = id
    override fun getVersion(): Int = 0
    override fun dependsOnFileContent(): Boolean = true
    override fun getIndexer(): DataIndexer<String, Void, FileContent> = Indexer()
    override fun getInputFilter(): FileBasedIndex.InputFilter = FileBasedIndex.InputFilter { file -> file.fileType is IRiotFileType }
    override fun getKeyDescriptor(): KeyDescriptor<String> = EnumeratorStringDescriptor.INSTANCE;

    class Indexer() : DataIndexer<String, Void, FileContent> {
        override fun map(context: FileContent): Map<String, Void?> {
            val psiFile = context.psiFile as? XmlFile ?: return emptyMap()
            val firstChild = psiFile.rootTag
            if (firstChild is XmlTag) {
                return mapOf(Pair(firstChild.name, null))
            }

            return emptyMap()
        }
    }


}