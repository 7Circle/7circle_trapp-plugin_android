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
    id("org.jetbrains.kotlin.jvm")  version "2.0.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

val trappsyncVersion: String by project

group = "dev.sevencircle.trappsync.plugin"
version = trappsyncVersion

dependencies {
    implementation(pluginLibs.kotlinx.serialization.json)
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("2.0.20")
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
    repositories {
        maven {
            name = "localPluginRepository"
            url = uri("../local-plugin-repository")
        }
    }
}

fun isReleaseBuild(): Boolean = System.getenv("IS_RELEASE") == "true"

if (isReleaseBuild()) apply(from = rootDir.resolve("signing.gradle.kts"))
