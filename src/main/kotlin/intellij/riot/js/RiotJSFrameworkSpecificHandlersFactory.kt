package intellij.riot.js

import com.intellij.lang.ecmascript6.psi.ES6ExportDefaultAssignment
import com.intellij.lang.javascript.frameworks.JSFrameworkSpecificHandlersFactory
import com.intellij.lang.javascript.psi.*
import com.intellij.lang.javascript.psi.types.*
import com.intellij.psi.PsiElement
import intellij.riot.lang.RiotHtmlLanguage

class RiotJSFrameworkSpecificHandlersFactory : JSFrameworkSpecificHandlersFactory {
    override fun findExpectedType(parent: JSExpression, expectedTypeKind: JSExpectedTypeKind): JSType? {
        val containingFile = parent.containingFile
        if (containingFile.language !is RiotHtmlLanguage ||
                parent !is JSObjectLiteralExpression ||
                parent.parent !is ES6ExportDefaultAssignment) {
            return null
        }

        return createLiteralType(parent)
    }

    private fun createLiteralType(parent: PsiElement): JSType {
        val anyType: JSAnyType = JSAnyType.get(parent, false)
        val simpleSource = JSTypeSourceFactory.createTypeSource(parent, true)
        val stringType = JSNamedTypeFactory.createType(JSCommonTypeNames.STRING_TYPE_NAME, simpleSource, JSContext.INSTANCE)
        val members = mutableListOf<JSRecordType.TypeMember>()
        members.add(JSRecordTypeImpl.PropertySignatureImpl("components", anyType, true, parent))
        members.add(JSRecordTypeImpl.PropertySignatureImpl("state", anyType, true, parent))
        members.add(JSRecordTypeImpl.PropertySignatureImpl("props", anyType, true, parent))
        members.add(JSRecordTypeImpl.PropertySignatureImpl("name", stringType, true, parent))
        members.add(createSimpleTwoArgumentFunction("shouldUpdate", simpleSource, anyType))
        members.add(createSimpleTwoArgumentFunction("onBeforeMount", simpleSource, anyType))
        members.add(createSimpleTwoArgumentFunction("onMounted", simpleSource, anyType))
        members.add(createSimpleTwoArgumentFunction("onBeforeUpdate", simpleSource, anyType))
        members.add(createSimpleTwoArgumentFunction("onUpdated", simpleSource, anyType))
        members.add(createSimpleTwoArgumentFunction("onUpdated", simpleSource, anyType))
        members.add(createSimpleTwoArgumentFunction("onBeforeUnmount", simpleSource, anyType))
        members.add(createSimpleTwoArgumentFunction("onUnmounted", simpleSource, anyType))

        return JSRecordTypeImpl(simpleSource, members)
    }

    private fun createSimpleTwoArgumentFunction(name: String, simpleSource: JSTypeSource, anyType: JSAnyType): JSRecordType.PropertySignature {
        val props = JSParameterTypeDecoratorImpl(anyType, true, false, false)
        val state = JSParameterTypeDecoratorImpl(anyType, true, false, false)
        val jsFunctionType = JSFunctionTypeImpl(simpleSource, listOf(props, state), null)
        return JSRecordTypeImpl.PropertySignatureImpl(name, jsFunctionType, true)
    }
}