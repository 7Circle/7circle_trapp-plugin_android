/*
 *  ______   ______    ______   __     __
 * /\  ___\ /\  __ \  /\__  _\ /\ \  _ \ \
 * \ \  __\ \ \ \/\ \ \/_/\ \/ \ \ \/ ".\ \
 *  \ \_\    \ \_____\   \ \_\  \ \__/".~\_\
 *   \/_/     \/_____/    \/_/   \/_/   \/_/
 *
 * Copyright © 2025 Zero12 S.r.l. All rights reserved.
 *  
 */

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */

plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
    `groovy-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`

    id("com.gradle.plugin-publish") version "1.2.1"
    id("org.jetbrains.kotlin.jvm")  version "1.9.22"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

fun getGitHubRepositoryUsername(): String =
    findProperty("GITHUB_USER")?.toString() ?: System.getenv("GITHUB_USER") ?: ""

fun getGitHubRepositoryPassword(): String =
    findProperty("GITHUB_TOKEN")?.toString() ?: System.getenv("GITHUB_TOKEN") ?: ""

val trappsyncVersion: String by project

group = "dev.sevencircle.trappsync.plugin"
version = trappsyncVersion

fun getGitHubRepositoryUsername(): String =
    findProperty("GITHUB_USER")?.toString() ?: System.getenv("GITHUB_USER") ?: ""

fun getGitHubRepositoryPassword(): String =
    findProperty("GITHUB_PACKAGES")?.toString() ?: System.getenv("GITHUB_PACKAGES") ?: ""

dependencies {
    implementation(pluginLibs.kotlinx.serialization.json)
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("1.9.22")
        }
    }
}

gradlePlugin {
    // Define the plugin
    plugins {
        val offlinePlugin by plugins.creating {
            id = "dev.sevencircle.trappsync.plugin.offline"
            implementationClass = "dev.sevencircle.trappsync.plugin.offline.OfflinePlugin"
            displayName = "TrAPP Sync Offline Plugin"
            description = "A Gradle plugin to generate offline files for TrAPP Sync"
            tags = listOf("trappsync", "offline")
            version = trappsyncVersion
            website = "https://github.com/7Circle/7circle_trappsync_plugin"
            vcsUrl = "https://github.com/7Circle/7circle_trappsync_plugin.git"
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("offlinePlugin") {
            afterEvaluate {
                // Adding the the generated .aar files.
                artifact("$projectDir/build/libs/$artifactId-$trappsyncVersion.jar")
                artifact("$projectDir/build/libs/$artifactId-$trappsyncVersion-javadoc.jar") {
                    classifier = "javadoc"
                }
                artifact("$projectDir/build/libs/$artifactId-$trappsyncVersion-sources.jar") {
                    classifier = "sources"
                }
            }
            version = trappsyncVersion
            groupId = "dev.sevencircle.trappsync.plugin.offline"
            artifactId = "dev.sevencircle.trappsync.plugin.offline.gradle.plugin"
            pom {
                name.set("TrappSync Offline Plugin")
                developers {
                    developer {
                        organization.set("Var Group S.p.A")
                        email.set("dev7circle@vargroup.it")
                        organizationUrl.set("https://www.vargroup.it/")
                    }
                }
                withXml {
                    val dependenciesNode = asNode().appendNode("dependencies")
                    configurations.getByName("implementation") {
                        dependencies.forEach {
                            val dependencyNode = dependenciesNode.appendNode("dependency")
                            dependencyNode.appendNode("groupId", it.group)
                            dependencyNode.appendNode("artifactId", it.name)
                            dependencyNode.appendNode("version", it.version)
                        }
                    }
                }
            }
        }
    }
    repositories {
        maven {
            name = "localPluginRepository"
            url = uri("../local-plugin-repository")
        }
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/7Circle/7circle_trapp_plugin_android")
            credentials {
                username = getGitHubRepositoryUsername()
                password = getGitHubRepositoryPassword()
            }
        }
    }
}

apply(from = rootDir.resolve("signing.gradle.kts"))