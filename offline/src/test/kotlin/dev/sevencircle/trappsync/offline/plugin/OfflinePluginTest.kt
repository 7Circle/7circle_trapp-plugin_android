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

package dev.sevencircle.trappsync.offline.plugin

import dev.sevencircle.trappsync.plugin.offline.OfflinePlugin
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

/**
 * Created by Samuele Pozzebon on 22/08/2024
 */


class OfflinePluginTest {
    @Test
    fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.extensions.extraProperties["trappsyncVersion"] = "version"
        project.plugins.apply(OfflinePlugin::class.java)
        // Verify the result
        assertNotNull(project.tasks.findByName("generateTrappFile"))
    }
}
