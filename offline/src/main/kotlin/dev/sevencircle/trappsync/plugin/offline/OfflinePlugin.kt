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

package dev.sevencircle.trappsync.plugin.offline

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.UnknownConfigurationException
import org.gradle.internal.impldep.org.bouncycastle.asn1.x500.style.RFC4519Style.description
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import java.time.Instant
import java.util.Date

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */

class OfflinePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        val configuration = project.extensions.create<OfflinePluginConfig>("trappsync")

        configuration.offlineJsonFile.convention(project.rootProject.layout.projectDirectory.file(JSON_FILE_NAME))

        // Use epoch milliseconds as default hash in order to force the update of the strings by the library
        configuration.offlineHash.convention(Instant.now().toEpochMilli().toString())

        project.tasks.register("generateTrappFile", GenerateOfflineFilesTask::class.java) {
            group = "TrappSync Offline"
            description = "Generate TrappSync offline files"

            offlineFileProperty = configuration.offlineJsonFile
            offlineHash = configuration.offlineHash
        }

        val trappsyncVersion: String by project

        try {
            project.allprojects.forEach { proj: Project? ->
                proj?.tasks?.getByName("preBuild")?.dependsOn("generateTrappFile")
            }
        } catch (e: Exception) {
            println("Unable to find build task")
        }

        project.dependencies {
            try {
                apply {
                    add("implementation", "dev.sevencircle.trappsync:core:$trappsyncVersion")
                }
            } catch (_: UnknownConfigurationException) {
            }
        }
    }
}
