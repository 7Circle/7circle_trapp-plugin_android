/*
 *  ______   ______    ______   __     __
 * /\  ___\ /\  __ \  /\__  _\ /\ \  _ \ \
 * \ \  __\ \ \ \/\ \ \/_/\ \/ \ \ \/ ".\ \
 *  \ \_\    \ \_____\   \ \_\  \ \__/".~\_\
 *   \/_/     \/_____/    \/_/   \/_/   \/_/
 *
 * Copyright © 2025 Var Group S.p.A. All rights reserved.
 *  
 */

package dev.sevencircle.trappsync.plugin.offline

import kotlinx.serialization.json.Json
import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.charset.Charset

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */

internal abstract class GenerateOfflineFilesTask : DefaultTask() {
    private val defaultFile: RegularFile = project.rootProject.layout.projectDirectory.file(JSON_FILE_NAME)

    @InputFiles
    val offlineFileProperty: RegularFileProperty = project.objects.fileProperty().convention(defaultFile)

    @Input
    val offlineHash: Property<String> = project.objects.property(String::class.java).convention(DEFAULT_HASH)

    @OutputDirectory
    val generatedDir: Provider<Directory> = project.layout.buildDirectory.dir("generated/source/trappsync/dev/sevencircle/trappsync/plugin/offline")

    @TaskAction
    fun action() {
        val offlineFile = offlineFileProperty.get().asFile

        // Creates output dir, if it doesn't already exists
        project.mkdir(generatedDir)

        val jsonParser = Json {
            ignoreUnknownKeys = true
        }

        val translations: OfflineJson = jsonParser.decodeFromString<OfflineJson>(offlineFile.readText(Charset.defaultCharset()))

        val resource = File(generatedDir.get().asFile, "OfflineLanguages.kt")
        resource.createNewFile()

        resource.writeText("package dev.sevencircle.trappsync.plugin.offline\n")
        resource.appendText("\n")
        resource.appendText("import dev.sevencircle.trappsync.core.domain.LanguageModel\n")
        resource.appendText("import dev.sevencircle.trappsync.core.domain.OfflineLanguages\n")
        resource.appendText("\n")
        resource.appendText("\n")
        resource.appendText("val parsedLanguages: OfflineLanguages = object : OfflineLanguages {\n")
        resource.appendText("\toverride val languageMap = setOf(\n")
        translations.translations.entries.forEach { (language, strings) ->
            resource.appendText("\t\tLanguageModel(\n")
            resource.appendText("\t\t\tlanguageCode = \"$language\",\n")
            resource.appendText("\t\t\ttranslationMap = mapOf(\n")
            strings.entries.forEach { (key, value) ->
                resource.appendText("\t\t\t\t\"$key\" to \"$value\",\n")
            }
            resource.appendText("\t\t\t),\n")
            resource.appendText("\t\thash = \"${offlineHash.get()}\"\n")
            resource.appendText("\t\t),\n")
        }
        resource.appendText("\t)\n\n")
        resource.appendText("\toverride val baseLanguage = \"${translations.baseLanguage}\"\n\n")
        resource.appendText("\toverride val localeMap = mapOf(\n")
        translations.languageFallback.entries.forEach { (key, value) ->
            resource.appendText("\t\t\"$key\" to \"$value\", \n")
        }
        resource.appendText("\t)\n")
        resource.appendText("}")
    }
}
