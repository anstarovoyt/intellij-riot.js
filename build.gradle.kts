import org.jetbrains.intellij.platform.gradle.TestFrameworkType


plugins {
    id("java") // Java support
    id("org.jetbrains.kotlin.jvm") version "2.3.20"
    id("org.jetbrains.intellij.platform") version "2.13.1"
}


version = "1.1.261"
group = "riot.js"

// Set the JVM language level used to build the project.
kotlin {
    jvmToolchain(21)
}


// Configure project's dependencies
repositories {
    mavenCentral()
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
        intellijIdea("261.22158.121")

        bundledPlugins(listOf("JavaScript", "com.intellij.css", "JavaScriptDebugger", "org.jetbrains.plugins.sass"))
        testBundledPlugins(
            listOf(
                "JavaScript",
                "com.intellij.css",
                "com.intellij.modules.json",
                "JavaScriptDebugger",
                "org.jetbrains.plugins.sass"
            )
        )

        testFramework(TestFrameworkType.Platform)
    }
}

intellijPlatform {
    pluginConfiguration {
        name = "Riot.js"
        version = "1.1.261"
        ideaVersion {
            sinceBuild = "261"
            untilBuild = "261.*"
        }
    }
}