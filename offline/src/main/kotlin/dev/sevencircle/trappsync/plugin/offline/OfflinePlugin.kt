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
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */

class OfflinePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        val configuration = project.extensions.create<OfflinePluginConfig>("trappsync")

        configuration.offlineJsonFile.convention(project.rootProject.layout.projectDirectory.file(JSON_FILE_NAME))
        configuration.offlineHash.convention(DEFAULT_HASH)

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
                    // TODO: Remember to change the version of trapp
                    add("implementation", "dev.sevencircle.trappsync:core:$trappsyncVersion")
                }
            } catch (_: UnknownConfigurationException) {
            }
        }
    }
}
