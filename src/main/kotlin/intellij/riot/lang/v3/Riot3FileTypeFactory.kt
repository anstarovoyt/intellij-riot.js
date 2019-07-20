package intellij.riot.lang.v3

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory

/**
 * File type factories are deprecated but we want to keep it for 2019.1 compatibility
 */
class Riot3FileTypeFactory : FileTypeFactory() {
    override fun createFileTypes(consumer: FileTypeConsumer) {
        consumer.consume(Riot3FileType.INSTANCE, "tag")
    }
}