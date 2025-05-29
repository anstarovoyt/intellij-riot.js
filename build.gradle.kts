import org.jetbrains.intellij.platform.gradle.TestFrameworkType


plugins {
    id("java") // Java support
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.intellij.platform") version "2.5.0"
}


version = "1.1.251"
group = "intellij.riot"

// Set the JVM language level used to build the project.
kotlin {
    jvmToolchain(21)
}


// Configure project's dependencies
repositories {
    mavenCentral()
//
    intellijPlatform {
        defaultRepositories()
        localPlatformArtifacts()
        intellijDependencies()
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.opentest4j:opentest4j:1.3.0")

    intellijPlatform {
        create("IU", "2025.1.1")

        bundledPlugins(listOf("JavaScript", "com.intellij.css", "JavaScriptDebugger", "org.jetbrains.plugins.sass"))

        testFramework(TestFrameworkType.Platform)
    }
}