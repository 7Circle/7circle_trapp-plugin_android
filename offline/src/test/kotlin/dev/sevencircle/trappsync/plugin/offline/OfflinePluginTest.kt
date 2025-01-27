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
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertNotNull

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */


class OfflinePluginTest {
    private fun isEqual(firstFile: Path, secondFile: Path): Boolean {
        if (Files.size(firstFile) != Files.size(secondFile)) {
            return false
        }
        Files.newBufferedReader(firstFile).use { bf1 ->
            Files.newBufferedReader(secondFile).use { bf2 ->
                var ch: Int
                while (bf1.read().also { ch = it } != -1) {
                    if (ch != bf2.read()) {
                        return false
                    }
                }
            }
        }
        return true
    }

    @Test
    fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.extensions.extraProperties["trappsyncVersion"] = "version"
        project.plugins.apply(OfflinePlugin::class.java)
        // Verify the result
        assertNotNull(project.tasks.findByName("generateTrappFile"))
    }

    @Test
    fun `test that the generated file is following the expected format`() {

        val json = File("src/test/resources/offline.json")

        val jsonParser = Json {
            ignoreUnknownKeys = true
        }

        val translations: OfflineJson = jsonParser.decodeFromString<OfflineJson>(json.readText(Charset.defaultCharset()))

        val res = File.createTempFile("offline_strings_", ".kt")
        val generatedFile = generateFile(
            resource = res,
            translations = translations,
            offlineHash = "1737707679811"
        )

        val file = File("src/test/resources/OfflineLanguages.kt")

        assert(isEqual(file.toPath(), generatedFile.toPath()))
    }
}
