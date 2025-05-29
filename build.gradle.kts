plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "2.0.20"
    id("org.jetbrains.intellij") version "1.17.4"
}

version = "1.1.242"
group = "intellij.riot"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2024.2")
    type.set("IU")
    plugins.set(listOf("JavaScript", "com.intellij.css", "JavaScriptDebugger", "org.jetbrains.plugins.sass"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("242.0")
        untilBuild.set("242.*")
    }

    buildPlugin {
        archiveBaseName.set("intellij-riot.js")
    }
}
