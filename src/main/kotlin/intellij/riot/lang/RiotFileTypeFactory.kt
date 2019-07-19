package intellij.riot.lang

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory

/**
 * File type factories are deprecated but we want to keep it for 2019.1 compatibility
 */
class RiotFileTypeFactory : FileTypeFactory() {
    override fun createFileTypes(consumer: FileTypeConsumer) {
        consumer.consume(RiotFileType.INSTANCE)
    }
}